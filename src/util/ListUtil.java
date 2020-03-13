package util;

import java.util.List;

public class ListUtil {
    /**
     * @author lilei
     * create at 2020/2/24 17:54
     * 将list按照page和size截取
     */
    public static <L> List<L> subList(int page, int size, List<L> list) {
        List<L> retList;
        //请求的页数大于数据库的数据
        if (list.size() < page * size) {
            return null;
        }
        if (list.size() <= size) {
            retList = list.subList(0, list.size());
        } else {
            if (list.size() < ((page + 1) * size)) {
                retList = list.subList(page * size, list.size());
            } else {
                retList = list.subList(page * size, (page + 1) * size);
            }
        }
        return retList;
    }
}
