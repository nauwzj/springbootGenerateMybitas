package ${packageName}.service;

import java.util.Map;

/**
* ${classInfo.classComment}
* @author ${authorName} ${.now?string('yyyy-MM-dd')}
*/
public interface ${classInfo.className}Service {

    /**
    * ${classInfo.className?uncap_first}
    * 新增
    */
    ReturnT<String> insert(${classInfo.className} ${classInfo.className?uncap_first});

    /**
    * ${classInfo.className?uncap_first}
    * 删除
    */
    ReturnT<String> delete(int id);

    /**
    * ${classInfo.className?uncap_first}
    * 更新
    */
    ReturnT<String> update(${classInfo.className} ${classInfo.className?uncap_first});

    /**
    * Load查询
    */
    ${classInfo.className} load(int id);

    /**

    * 分页查询
    */
    Map<String,Object> pageList(int offset, int pagesize);

}
