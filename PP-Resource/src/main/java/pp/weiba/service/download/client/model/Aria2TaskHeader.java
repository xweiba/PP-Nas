package pp.weiba.service.download.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @description AddParamHeader
 * @date 2023/6/1 0:11
 */
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2TaskHeader {

    @Builder.Default
    List<String> header = new ArrayList<>();

}
