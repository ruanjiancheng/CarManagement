import Vue from 'vue';
import VueRouter from 'vue-router';
import login from '../views/login/index';
import worker from '../views/worker/index'
import bizmen from '../views/bizmen/index'
import client from '../views/client/index'
import clientHome from '../views/client/home'
import workerHome from '../views/worker/home'
import bizmenHome from '../views/bizmen/home'
import workerManage from "@/views/bizmen/wokerManage/index"
import bizmenManage from "@/views/bizmen/bizmenManage/index"
import clientManage from "@/views/bizmen/clientManage/index"
import queryWorker from "@/views/bizmen/wokerManage/queryWorker";
import addWorker from "@/views/bizmen/wokerManage/addWorker";
import editorWorker from "@/views/bizmen/wokerManage/editorWorker";
import queryBizmen from "@/views/bizmen/bizmenManage/queryBizmen";
import editorBizmen from "@/views/bizmen/bizmenManage/editorBizmen";
import addBizmen from "@/views/bizmen/bizmenManage/addBizmen";
import carList from "@/views/client/carList";
import carIndex from "@/views/client/carIndex";
import queryClient from "@/views/bizmen/clientManage/clientManage/queryClient";
import addCar from "@/views/bizmen/clientManage/carManage/addCar";
import showClientCar from "@/views/bizmen/clientManage/carManage/showClientCar";
import materialList from "@/views/bizmen/materialManage/materialList";
import addMaterial from "@/views/bizmen/materialManage/addMaterial";
import editorCar from "@/views/bizmen/clientManage/carManage/editorCar";
import car_list from "@/views/bizmen/clientManage/carManage/carList";
import addClient from "@/views/bizmen/clientManage/clientManage/addClient";
import editorClient from "@/views/bizmen/clientManage/clientManage/editorClient";
import addRepairment from "@/views/bizmen/repairmentManage/addRepairment";
import repairmentList from "@/views/bizmen/repairmentManage/repairmentList";
import editorRepairment from "@/views/bizmen/repairmentManage/editorRepairment";
import repairmentDetail from "@/views/bizmen/repairmentManage/repairmentDetail";
import addCommission from "@/views/bizmen/commissionManage/addCommission";
import commList from "@/views/bizmen/commissionManage/commList";
import orderList from "@/views/bizmen/orderManage/orderList";
import orderDetail from "@/views/bizmen/orderManage/orderDetail";
import orderAddRepairment from "@/views/bizmen/orderManage/orderAddRepairment";
import unFinish from "@/views/worker/orderManage/unFinish";
import finish from "@/views/worker/orderManage/finish";
import comm from "@/views/client/comm";
import detail from "@/views/bizmen/commissionManage/detail";

Vue.use(VueRouter)

const routes = [
  {
    // 随便定义的首页
    path: '/',
    name: 'index',
    component: login,
    redirect: '/login'
  },
  {
    // 登陆页
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/bizmen',
    name: 'bizmen',
    component: bizmen,
    redirect: '/bizmenHome',
    meta: {requireAuth: true},
    children: [
      {
        path: '/bizmen',
        name: 'hi! bizmen',
        component: bizmenHome,
        meta: {requireAuth: true},
        children: [
          {
            path: '/bizmenHome',
            name: '业务员主页',
            component: bizmenHome,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/commissionManage',
        name: '委托书管理',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/addCommission',
            name: '添加委托书',
            component: addCommission,
            meta: {requireAuth: true},
          },
          {
            path: '/commList',
            name: '委托书列表',
            component: commList,
            meta: {requireAuth: true},
          },
          {
            path: '/detail',
            name: '*',
            component: detail,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/orderManage',
        name: '工单管理',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/orderList',
            name: '工单列表',
            component: orderList,
            meta: {requireAuth: true},
          },
          {
            path: '/orderDetail',
            name: '*工单详细',
            component: orderDetail,
            meta: {requireAuth: true},
          },
          {
            path: '/orderAddRepairment',
            name: '*配置工单',
            component: orderAddRepairment,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/repairmentManage',
        name: '维修项目管理',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/addRepairment',
            name: '添加维修项目',
            component: addRepairment,
            meta: {requireAuth: true}
          },
          {
            path: '/repairmentList',
            name: '维修项目列表',
            component: repairmentList,
            meta: {requireAuth: true}
          },
          {
            path: '/repairmentAddMaterial',
            name: '*编辑维修项目',
            component: editorRepairment,
            meta: {requireAuth: true}
          },
          {
            path: '/repairmentDetail',
            name: '*维修项目细节',
            component: repairmentDetail,
            meta: {requireAuth: true}
          },
        ]
      },
      {
        path: '/materialManage',
        name: '材料管理',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/materialList',
            name: '材料列表',
            component: materialList,
            meta: {requireAuth: true}
          },
          {
            path: '/addMaterial',
            name: '添加材料',
            component: addMaterial,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/carManage',
        name: '车辆管理',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/car_list',
            name: '车辆列表',
            component: car_list,
            meta: {requireAuth: true}
          },
          {
            path: '/addCar',
            name: '添加车辆',
            component: addCar,
            meta: {requireAuth: true}
          },
          {
            path: '/editorCar',
            name: '*编辑车辆',
            component: editorCar,
            meta: {requireAuth: true}
          },
          {
            path: '/showClientCar',
            name: '*查看用户车辆',
            component: showClientCar,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/clientManage',
        name: '顾客管理',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/queryClient',
            name: '客户管理',
            component: queryClient,
            meta: {requireAuth: true}
          },

          {
            path: '/addClient',
            name: '添加客户',
            component: addClient,
            meta: {requireAuth: true}
          },

          {
            path: '/editorClient',
            name: '*编辑客户',
            component: editorClient,
            meta: {requireAuth: true}
          },
        ]
      },
      {
        path: '/bizmenManage',
        name: '业务员管理',
        component: bizmenManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/queryBizmen',
            name: '业务员列表',
            component: queryBizmen,
            meta: {requireAuth: true}
          },
          {
            path: '/addBizmen',
            name: '添加业务员',
            component: addBizmen,
            meta: {requireAuth: true}
          },
          {
            path: '/editorBizmen',
            name: '*业务员编辑',
            component: editorBizmen,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/workerManage',
        name: '维修员管理',
        component: workerManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/queryWorker',
            name: '维修员列表',
            component: queryWorker,
            meta: {requireAuth: true}
          },
          {
            path: '/addWorker',
            name: '添加维修员',
            component: addWorker,
            meta: {requireAuth: true},
          },
          {
            path: '/editorWorker',
            name: '*编辑维修员',
            component: editorWorker,
            meta: {requireAuth: true},
          },
        ]
      },
    ]
  },
  {
    path: '/worker',
    name: 'worker',
    component: worker,
    redirect: '/workerHome',
    meta: {requireAuth: true},
    children: [
      {
        path: '/worker',
        name: 'hi! worker',
        component: workerHome,
        meta: {requireAuth: true},
        children: [
          {
            path: '/workerHome',
            name: '维修员主页',
            component: workerHome,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/workerTask',
        name: '维修任务管理',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/taskFinish',
            name: '未完成的任务',
            component: unFinish,
            meta: {requireAuth: true},
          },
          {
            path: '/taskUnFinish',
            name: '历史任务',
            component: finish,
            meta: {requireAuth: true},
          },
        ],
      },
    ]
  },
  {
    path: '/client',
    name: 'client',
    component: client,
    redirect: '/clientHome',
    meta: {requireAuth: true},
    children: [
      {
        path: '/clientHome',
        name: 'hi! client',
        component: clientHome,
        meta: {requireAuth: true},
        children: [
          {
            path: '/clientHome',
            name: '客户主页',
            component: clientHome,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/carIndex',
        name: '车辆管理',
        component: carIndex,
        meta: {requireAuth: true},
        children: [
          {
            path: '/carList',
            name: '查看我的车辆',
            component: carList,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/commFinder',
        name: '委托查看',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/commFind',
            name: '委托列表',
            component: comm,
            meta: {requireAuth: true},
          },
        ],
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

/*
  session 设置：
    1. token
    2. name
    3. type
    4. id
 */
router.beforeEach((to, from, next) => {
  console.log(from.path + ' ====> ' + to.path)
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (sessionStorage.getItem("token") === 'true') { // 判断本地是否存在token
      next()
    } else {
      // 未登录,跳转到登陆页面
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    // 不需要登陆权限的页面，如果已经登陆，则跳转主页面
    if(sessionStorage.getItem("token") === 'true'){
      console.log('检查拦截器配置，大概率出现漏网之鱼')
      const t = sessionStorage.getItem("type")
      next('/' + t);
    }else{
      next();
    }
  }
});