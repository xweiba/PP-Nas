package pp.weiba.service.download.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 任务配置信息
 * @date 2023/6/1 0:32
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2TaskOption {

    @JSONField(name = "allow-overwrite")
    private String allowoverwrite;
    @JSONField(name = "allow-piece-length-change")
    private String allowpiecelengthchange;
    @JSONField(name = "always-resume")
    private String alwaysresume;
    @JSONField(name = "async-dns")
    private String asyncdns;
    @JSONField(name = "auto-file-renaming")
    private String autofilerenaming;
    @JSONField(name = "bt-enable-hook-after-hash-check")
    private String btenablehookafterhashcheck;
    @JSONField(name = "bt-enable-lpd")
    private String btenablelpd;
    @JSONField(name = "bt-force-encryption")
    private String btforceencryption;
    @JSONField(name = "bt-hash-check-seed")
    private String bthashcheckseed;
    @JSONField(name = "bt-load-saved-metadata")
    private String btloadsavedmetadata;
    @JSONField(name = "bt-max-peers")
    private String btmaxpeers;
    @JSONField(name = "bt-metadata-only")
    private String btmetadataonly;
    @JSONField(name = "bt-min-crypto-level")
    private String btmincryptolevel;
    @JSONField(name = "bt-prioritize-piece")
    private String btprioritizepiece;
    @JSONField(name = "bt-remove-unselected-file")
    private String btremoveunselectedfile;
    @JSONField(name = "bt-request-peer-speed-limit")
    private String btrequestpeerspeedlimit;
    @JSONField(name = "bt-require-crypto")
    private String btrequirecrypto;
    @JSONField(name = "bt-save-metadata")
    private String btsavemetadata;
    @JSONField(name = "bt-seed-unverified")
    private String btseedunverified;
    @JSONField(name = "bt-stop-timeout")
    private String btstoptimeout;
    @JSONField(name = "bt-tracker")
    private String bttracker;
    @JSONField(name = "bt-tracker-connect-timeout")
    private String bttrackerconnecttimeout;
    @JSONField(name = "bt-tracker-interval")
    private String bttrackerinterval;
    @JSONField(name = "bt-tracker-timeout")
    private String bttrackertimeout;
    @JSONField(name = "check-integrity")
    private String checkintegrity;
    @JSONField(name = "conditional-get")
    private String conditionalget;
    @JSONField(name = "connect-timeout")
    private String connecttimeout;
    @JSONField(name = "content-disposition-default-utf8")
    private String contentdispositiondefaultutf8;
    @JSONField(name = "continue")
    private Boolean continueX;
    private String dir;
    private String out;
    @JSONField(name = "dry-run")
    private String dryrun;
    @JSONField(name = "enable-http-keep-alive")
    private String enablehttpkeepalive;
    @JSONField(name = "enable-http-pipelining")
    private String enablehttppipelining;
    @JSONField(name = "enable-mmap")
    private String enablemmap;
    @JSONField(name = "enable-peer-exchange")
    private String enablepeerexchange;
    @JSONField(name = "file-allocation")
    private String fileallocation;
    @JSONField(name = "follow-metalink")
    private String followmetalink;
    @JSONField(name = "follow-torrent")
    private String followtorrent;
    @JSONField(name = "force-save")
    private String forcesave;
    @JSONField(name = "ftp-pasv")
    private String ftppasv;
    @JSONField(name = "ftp-reuse-connection")
    private String ftpreuseconnection;
    @JSONField(name = "ftp-type")
    private String ftptype;
    @JSONField(name = "hash-check-only")
    private String hashcheckonly;
    private List<String> header;
    @JSONField(name = "http-accept-gzip")
    private String httpacceptgzip;
    @JSONField(name = "http-auth-challenge")
    private String httpauthchallenge;
    @JSONField(name = "http-no-cache")
    private String httpnocache;
    @JSONField(name = "http-want-digest")
    private String httpwantdigest;
    @JSONField(name = "lowest-speed-limit")
    private String lowestspeedlimit;
    @JSONField(name = "max-connection-per-server")
    private String maxconnectionperserver;
    @JSONField(name = "max-download-limit")
    private String maxdownloadlimit;
    @JSONField(name = "max-file-not-found")
    private String maxfilenotfound;
    @JSONField(name = "max-mmap-limit")
    private String maxmmaplimit;
    @JSONField(name = "max-resume-failure-tries")
    private String maxresumefailuretries;
    @JSONField(name = "max-tries")
    private String maxtries;
    @JSONField(name = "max-upload-limit")
    private String maxuploadlimit;
    @JSONField(name = "metalink-enable-unique-protocol")
    private String metalinkenableuniqueprotocol;
    @JSONField(name = "metalink-preferred-protocol")
    private String metalinkpreferredprotocol;
    @JSONField(name = "min-split-size")
    private String minsplitsize;
    @JSONField(name = "no-file-allocation-limit")
    private String nofileallocationlimit;
    @JSONField(name = "no-netrc")
    private String nonetrc;
    @JSONField(name = "parameterized-uri")
    private String parameterizeduri;
    @JSONField(name = "pause-metadata")
    private String pausemetadata;
    @JSONField(name = "piece-length")
    private String piecelength;
    @JSONField(name = "proxy-method")
    private String proxymethod;
    @JSONField(name = "realtime-chunk-checksum")
    private String realtimechunkchecksum;
    @JSONField(name = "remote-time")
    private String remotetime;
    @JSONField(name = "remove-control-file")
    private String removecontrolfile;
    @JSONField(name = "retry-on-400")
    private String retryon400;
    @JSONField(name = "retry-on-403")
    private String retryon403;
    @JSONField(name = "retry-on-406")
    private String retryon406;
    @JSONField(name = "retry-on-unknown")
    private String retryonunknown;
    @JSONField(name = "retry-wait")
    private String retrywait;
    @JSONField(name = "reuse-uri")
    private String reuseuri;
    @JSONField(name = "rpc-save-upload-metadata")
    private String rpcsaveuploadmetadata;
    @JSONField(name = "save-not-found")
    private String savenotfound;
    @JSONField(name = "seed-ratio")
    private String seedratio;
    @JSONField(name = "seed-time")
    private String seedtime;
    private String split;
    @JSONField(name = "stream-piece-selector")
    private String streampieceselector;
    private String timeout;
    @JSONField(name = "uri-selector")
    private String uriselector;
    @JSONField(name = "use-head")
    private String usehead;
    @JSONField(name = "user-agent")
    private String useragent;
}
