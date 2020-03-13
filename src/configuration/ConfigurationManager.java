//package configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Wenn
// * Created at 1:04 PM, 2019-09-09
// * 配置文件管理器：统一的 YAML 配置文件处理模块。
// */
//public class ConfigurationManager {
//
//    private static Map<String, ConfigurationLoader> loaderMap = new HashMap<>();
//
//    /**
//     * 取得配置文件信息。
//     * @param modelClass 配置文件模型类对象。
//     * @param <Model> 配置文件模型类，必须继承自 {@link ConfigurationModel}。
//     * @return 包含配置信息的对象。
//     */
//    public static <Model extends ConfigurationModel> Model getConfiguration(Class<Model> modelClass){
//        String className = modelClass.getName();
//        return getConfiguration(modelClass, className);
//    }
//
//    /**
//     * 取得指定标识符对应的配置文件信息。
//     * 此标识符对应的配置必须在此之前经 {@link #createConfiguration(Class, String, String, String)} 声明创建。
//     * @param modelClass 配置文件模型类对象。
//     * @param identifier 标识符。
//     * @param <Model> 配置文件模型类，必须继承自 {@link ConfigurationModel}。
//     * @return 包含配置信息的对象。
//     */
//    public static <Model extends ConfigurationModel> Model getConfiguration(Class<Model> modelClass, String identifier){
//        ConfigurationLoader<Model> loader;
//        if(loaderMap.containsKey(identifier)){
//            loader = loaderMap.get(identifier);
//        }else{
//            loader = new ConfigurationLoader<Model>(
//                    modelClass
//            );
//            loaderMap.put(
//                    modelClass.getName(),
//                    loader
//            );
//        }
//        return loader.getConfiguration();
//    }
//
//    /**
//     * 创建和声明带有标识符的配置文件。
//     * 此方法用于要加载的配置文件的默认地址或命令行参数名不同于 {@link Model} 默认值的情形。
//     * 必须先使用此方法声明创建配置文件信息，随后才能使用 {@link #getConfiguration(Class, String)} 取用配置文件。
//     * @param modelClass 配置文件模型类对象。
//     * @param identifier 标识符。
//     * @param commandArgumentKey 命令行参数名。
//     * @param defaultConfigurationPath 默认的配置文件路径。
//     * @param <Model> 配置文件模型类，必须继承自 {@link ConfigurationModel}。
//     */
//    public static <Model extends ConfigurationModel> void createConfiguration(Class<Model> modelClass, String identifier, String commandArgumentKey, String defaultConfigurationPath){
//        if(loaderMap.containsKey(identifier)){
//            return;
//        }
//        ConfigurationLoader<Model> loader = new ConfigurationLoader<>(
//                modelClass, commandArgumentKey, defaultConfigurationPath
//        );
//        loaderMap.put(
//                identifier, loader
//        );
//    }
//
//}
