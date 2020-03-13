//package configuration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//
///**
// * @author Wenn
// * Created at 11:51 AM, 2019-09-09
// * 配置文件加载模块。
// * 注意： 除 Common Library 内部，任何系统不应直接调用到 {@link ConfigurationLoader} 。
// *       请使用 {@link ConfigurationManager} 来处理和加载配置。
// * 注意： 1 个 ConfigurationLoader 只用于处理 1 个配置文件。
// */
//public class ConfigurationLoader<Model extends ConfigurationModel> {
//
//    /**
//     * 配置文件实例。
//     */
//    private Model model;
//
//    /**
//     * 命令行参数 Key 。
//     */
//    private String commandArgumentKey;
//
//    /**
//     * 配置文件路径。
//     */
//    private String configurationPath;
//
//    /**
//     * 构造一个 ConfigurationLoader ，指定命令行参数、配置文件地址并并加载对应配置文件。
//     * @param clazz 配置文件模型类。
//     * @param commandArgumentKey 命令行参数名。
//     * @param configurationPath 配置文件路径。
//     */
//    public ConfigurationLoader(Class<Model> clazz, String commandArgumentKey, String configurationPath){
//        this.commandArgumentKey = commandArgumentKey;
//        this.configurationPath = configurationPath;
//        init(clazz);
//    }
//
//    /**
//     * 使用 Model 的默认配置加载配置文件。
//     * @param clazz 配置文件模型类。
//     */
//    public ConfigurationLoader(Class<Model> clazz){
//        try {
//            // Instantiate a model to get its `Command Argument Key` and `Default Configuration Path`
//            // Static method could not be override, that's why.
//            Model model = clazz.getDeclaredConstructor().newInstance();
//            this.commandArgumentKey = model.getDefaultCommandArgumentKey();
//            this.configurationPath = model.getDefaultConfigurationPath();
//            init(clazz);
//        }catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 加载配置文件。
//     * @param clazz 配置文件模型类。
//     */
//    private void init(Class<Model> clazz){
//        String configPath = System.getProperty( commandArgumentKey, configurationPath );
//
//        ObjectMapper mapper = new ObjectMapper( new YAMLFactory() );
//
//        try{
//
//            model = mapper.readValue(
//                    new File(configPath), clazz
//            );
//
//        }catch (IOException e){
//
//            throw new RuntimeException(e);
//
//        }
//
//    }
//
//    /**
//     * 取得配置对象。
//     * @return 包含配置信息的对象。
//     */
//    public Model getConfiguration() {
//        return model;
//    }
//}
