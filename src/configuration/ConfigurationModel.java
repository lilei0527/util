//package configuration;
//
///**
// * @author Wenn
// * Created at 11:29 AM, 2019-09-09
// * 配置文件模板的基础类，所有要加载的配置文件都应该继承此类来进行读取。
// */
//public abstract class ConfigurationModel {
//
//    /**
//     * 提供默认的配置文件地址。如 {@code "./config/demo.yml"}
//     * 在启动程序时，如未通过参数指定配置文件地址，则将默认加载这里指出的配置文件。
//     * @return 默认的配置文件地址。
//     */
//    public abstract String getDefaultConfigurationPath();
//
//    /**
//     * 提供默认的命令行参数 Key 。
//     * 在启动程序时，如需要为这一项配置指定配置文件地址，则这一参数的默认键名在此方法提供。
//     * 例如，在启动程序时，希望读取非默认的配置文件 "./test-config/test-demo.yml" ，通过命令行参数传递此地址：
//     * {@code java -jar demo.jar -DdemoConfig='./test-config/test-demo.yml'}
//     * 此时要在此方法指出的“默认的命令行参数 Key”即为 {@code "demoConfig"}
//     * @return 默认的命令行参数 Key 。
//     */
//    public abstract String getDefaultCommandArgumentKey();
//
//}
