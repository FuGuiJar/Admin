package top.fuguijar.constants;

/** App Const
 * @author fuguijar.top
 * @date 2021-01-31
 */
public class AppConstants {
    public final static String STATIC_RESOURCES_NAME = "static";
    public final static String STATIC_PROJECT_NAME= "admin";
    public final static String TOKEN= "Authorization";

    /*result 响应码*/
    public final static Integer RESULT_CODE_OK = 200;
    public final static Integer RESULT_CODE_UNAUTHORIZED = 401;
    public final static Integer RESULT_CODE_NOTFOUND = 404;
    public final static Integer RESULT_CODE_SERVERERROR = 500;
    public final static Integer RESULT_CODE_REDIRECT = 302;


    public static void ADMIN_ERP(){
        System.out.println("    _       _           _         _____ ____  ____  \n" +
                "   / \\   __| |_ __ ___ (_)_ __   | ____|  _ \\|  _ \\ \n" +
                "  / _ \\ / _` | '_ ` _ \\| | '_ \\  |  _| | |_) | |_) |\n" +
                " / ___ \\ (_| | | | | | | | | | | | |___|  _ <|  __/ \n" +
                "/_/   \\_\\__,_|_| |_| |_|_|_| |_| |_____|_| \\_\\_|    \n" +
                "                                                    V1.0\n ");
    }

    public static void FUGUIJAR(){
        System.out.println(" _____       ____       _     _            \n" +
                "|  ___|   _ / ___|_   _(_)   | | __ _ _ __ \n" +
                "| |_ | | | | |  _| | | | |_  | |/ _` | '__|\n" +
                "|  _|| |_| | |_| | |_| | | |_| | (_| | |   \n" +
                "|_|   \\__,_|\\____|\\__,_|_|\\___/ \\__,_|_|   \n" +
                "                                          @版权所有 ");
    }
}
