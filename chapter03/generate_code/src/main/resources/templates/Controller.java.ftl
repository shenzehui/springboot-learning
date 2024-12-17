package ${packageName}.controller;

import ${packageName}.model.${modelName};
import ${packageName}.service.${serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

/**
 * <p>
 * ${tableDescription} Mapper 类
 * </p>
 *
 * @author 沈泽辉
 */
@AllArgsConstructor
@RestController
@RequestMapping("")
@Tag(name = "tableDescription")
@RestController
public class ${controllerName} {

    @Autowired
    private final ${serviceName} ${serviceName?uncap_first};

}