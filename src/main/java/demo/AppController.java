package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Value("${git.commit.id}")
    private String gitCommitId;

    @Value("${info.service_name}")
    private String serviceName;

    @Value("${info.version}")
    private String serviceVersion;

    @Value(("${logging.level.root}"))
    private String logLevel;

    @Value(("${server.port}"))
    private String serverPort;

    @RequestMapping("/")
    public String greet() {
        LOGGER.info("Loading application root context...");
        return "Hello World! To see info about this application append \"/info\" to the URL";
    }

    @RequestMapping("/info")
    public Map<String, Object> getAppInfo() {
        LOGGER.info("Loading \"info\" endpoint...");
        Map<String, String> env = new HashMap<>();
        env.put ("service_port", serverPort);
        env.put ("log_level", logLevel);

        HashMap<String, Object> infoMap = new HashMap<>();

        infoMap.put("service_name", serviceName);
        infoMap.put("version", serviceVersion);
        infoMap.put("git_commit_sha", gitCommitId);
        infoMap.put("environment", env);

        return infoMap;
    }
}
