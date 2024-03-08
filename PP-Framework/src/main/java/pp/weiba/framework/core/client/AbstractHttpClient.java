package pp.weiba.framework.core.client;

import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IDataProcessor;
import pp.weiba.framework.core.convert.ITypeReferenceProcessor;
import pp.weiba.framework.core.convert.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient 客户端
 *
 * @author weiba
 * @date 2024/3/7 10:29
 */
@Log4j2
public abstract class AbstractHttpClient<T, F> implements IHttpClient {

    private final IHttpTypeAdapter<T, F> httpTypeAdapter;
    private final ITypeReferenceProcessor<String> typeReferenceProcessor;
    private List<IDataProcessor<HttpRequest>> requestProcessors;
    private List<IDataProcessor<HttpResponse>> responseProcessors;


    public AbstractHttpClient(IHttpTypeAdapter<T, F> httpTypeAdapter, ITypeReferenceProcessor<String> typeReferenceProcessor) {
        this.httpTypeAdapter = httpTypeAdapter;
        this.typeReferenceProcessor = typeReferenceProcessor;
    }

    @Override
    public HttpResponse execute(HttpRequest request) {
        // 前置过滤
        request = dataProcess(requestProcessors, request);

        if (log.isDebugEnabled()) {
            log.debug("execute request: {}", JSONUtil.toJsonStr(request));
        }

        // 适配请求数据
        T adapterRequest = httpTypeAdapter.adapter(request);

        // 执行请求
        F executeRequest = execute(adapterRequest);
        // 适配响应数据
        HttpResponse adapterResponse = httpTypeAdapter.adapter(executeRequest);
        // 后置过滤
        adapterResponse = dataProcess(responseProcessors, adapterResponse);
        return adapterResponse;
    }

    @Override
    public <T> T execute(HttpRequest request, TypeReference<T> typeReference) {
        HttpResponse response = execute(request);
        return typeReferenceProcessor.process(response.getBody(), typeReference);
    }

    protected abstract F execute(T request);

    @Override
    public void addRequestDataProcessor(IDataProcessor<HttpRequest> httpRequestProcessor) {
        requestProcessors = addDataProcessor(requestProcessors, httpRequestProcessor);
    }

    @Override
    public void addResponseDataProcessor(IDataProcessor<HttpResponse> httpResponseProcessor) {
        responseProcessors = addDataProcessor(responseProcessors, httpResponseProcessor);
    }

    /**
     * 数据处理
     *
     * @param list 集合
     * @param data 数据
     * @return 数据
     * @author weiba
     * @date 2024/3/7 10:35
     */
    private <E> E dataProcess(List<IDataProcessor<E>> list, E data) {
        if (null != list) {
            for (IDataProcessor<E> dataProcessor : list) {
                data = dataProcessor.process(data);
            }
        }
        return data;
    }

    /**
     * 添加数据处理器
     *
     * @param list             集合
     * @param addDataProcessor 添加数据处理器
     * @return 集合
     * @author weiba
     * @date 2024/3/7 10:36
     */
    private <E> List<IDataProcessor<E>> addDataProcessor(List<IDataProcessor<E>> list, IDataProcessor<E> addDataProcessor) {
        if (null == list) {
            list = new ArrayList<>();
        }

        if (checkListContains(list, addDataProcessor)) {
            list.add(addDataProcessor);
        }
        return list;
    }


    /**
     * 集合中是否包含某个类型的元素
     *
     * @param list     集合
     * @param checkObj 检查对象
     * @return 是or否
     * @author weiba
     * @date 2024/3/7 10:37
     */
    private <E> boolean checkListContains(List<E> list, Object checkObj) {
        if (list == null || checkObj == null) {
            return false;
        }
        return list.stream().noneMatch(responseFilter -> responseFilter.getClass().equals(checkObj.getClass()));
    }
}
