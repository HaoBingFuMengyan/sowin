package com.hbf.sys;

import com.hbf.exception.ServiceException;
import com.hbf.jpa.BaseDao;
import com.hbf.jpa.BaseService;
import com.hbf.utils.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by haobingfu on 2018/8/5.
 */
@Component
@Transactional
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuDao menuDao;

    @Override
    protected BaseDao<Menu, String> getBaseDao() {
        return menuDao;
    }

    @Override
    protected void BaseSaveCheck(Menu obj) {

        obj.setDadddate(new Date());

    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @Transactional( readOnly = true)
    public List<Menu> getAllMenu() {
        return menuDao.findAll(new Sort(new Order(Sort.Direction.ASC, "isort")));
    }
    /**
     * 根据菜单id查询菜单
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Menu getMenuById(String id) {
        return menuDao.findOne(id);
    }

    /**
     * 保存菜单
     *
     * @param menu
     * @param id
     */
    public void saveMenu(Menu menu, String id) throws ServiceException {
        try {
            if (id == null) {// 如果id为空，则为添加
                menu.setId(null);
                // 先设置父节点不是叶子节点
                Menu menu1 = getMenuById(menu.getSparentid());
                if (menu1.getBisleaf() == 1) {
                    menu1.setBisleaf(0);
                    menuDao.save(menu1);
                }
                List<Menu> lm = getMenuByParentId(menu.getSparentid(), "desc");
                if (!lm.isEmpty() && lm.size() > 0) {
                    menu.setIsort(((Menu) lm.get(0)).getIsort() + 1);// 设置排序号
                    // menu.setSmenuno(String.valueOf(((Menu)lm.get(0)).getIsort(
                    // )+ 1));
                } else {
                    menu.setIsort(menu1.getIsort() * 100);
                    // menu.setSmenuno(String.valueOf(menu1.getIsort() * 100));
                }

                menu.setSmenuno(/*menuDao.getSeqNo(Colums.sys_menu.tablename)*/"000000");
                // 设置节点
                menu.setBisleaf(1);
                menu.setBisroot(0);
                menu.setDadddate(T.now());
            }else{//修改
                Menu menuObj = menuDao.findOne(id);
                subMenu(menuObj.getId(),menuObj.getIsort(),menu.getIsort());
            }
            menuDao.save(menu);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("保存菜单失败");
        }
    }

    /**
     * 根据父Id查询菜单
     *
     * @param parentId
     *            父Id
     * @return
     */
    @Transactional(readOnly = true)
    public List<Menu> getMenuByParentId(String parentId, String sort) {
        Sort ss;
        if(sort.equals("desc")){
            ss=new Sort(new Order(Direction.DESC,"isort"));
        }
        else
            ss=new Sort(new Order(Direction.ASC,"isort"));
        return menuDao.getMenuByParentId(parentId, ss);
    }

    /**
     *修改子节点的排序号
     * @param parentid 父节点ID
     * @param oldisort
     * @param newisort
     */
    public void subMenu(String parentid,Long oldisort,Long newisort){
        List<Menu> subMenu = menuDao.getMenuByParentId(parentid,null);//查询所有的子节点
        for(Menu obj:subMenu){
            StringBuilder bf = new StringBuilder();
            int index = String.valueOf(oldisort).length();
            int endindex = String.valueOf(obj.getIsort()).length();
            String isort = String.valueOf(obj.getIsort()).substring(index,endindex);
            String nwisort = String.valueOf(newisort);
            obj.setIsort(Long.valueOf(bf.append(nwisort).append(isort).toString()));
            subMenu(obj.getId(),oldisort,newisort);
        }
    }

    /**
     * 判断名称是否重复
     *
     * @param sname
     * @return
     */
    @Transactional(readOnly = true)
    public boolean isNameSame(String sname, String sparentid, String id)
            throws ServiceException {
        try {
            if(id!=null){
                return menuDao.count(sname, sparentid, id)>1;

            }else{
                return menuDao.count(sname, sparentid)==0;
            }

        } catch (Exception ex) {
            //	LogUtils.log(ex.getMessage());
            ex.printStackTrace();
            throw new ServiceException("名称已存在！" + ex.getMessage());
        }
    }
}
