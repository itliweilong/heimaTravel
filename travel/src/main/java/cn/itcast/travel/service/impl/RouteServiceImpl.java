package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService{
    private RouteDao dao = new RouteDaoImpl();
    private RouteImgDao imgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> pb = new PageBean<Route>();
        //存进当前页码
        pb.setCurrentPage(currentPage);
        //存进每页数据条数
        pb.setPageSize(pageSize);
        //存进总记录数
        int totalCount = dao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //存进一当前页内数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> list = dao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);
        //存进总页数
        int totalPage= totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) +1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route = dao.findOne(Integer.parseInt(rid));

        List<RouteImg> routeImgList = imgDao.findByRid(route.getRid());
        route.setRouteImgList(routeImgList);

        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
