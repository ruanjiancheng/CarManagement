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
    // ?????????????????????
    path: '/',
    name: 'index',
    component: login,
    redirect: '/login'
  },
  {
    // ?????????
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
            name: '???????????????',
            component: bizmenHome,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/commissionManage',
        name: '???????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/addCommission',
            name: '???????????????',
            component: addCommission,
            meta: {requireAuth: true},
          },
          {
            path: '/commList',
            name: '???????????????',
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
        name: '????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/orderList',
            name: '????????????',
            component: orderList,
            meta: {requireAuth: true},
          },
          {
            path: '/orderDetail',
            name: '*????????????',
            component: orderDetail,
            meta: {requireAuth: true},
          },
          {
            path: '/orderAddRepairment',
            name: '*????????????',
            component: orderAddRepairment,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/repairmentManage',
        name: '??????????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/addRepairment',
            name: '??????????????????',
            component: addRepairment,
            meta: {requireAuth: true}
          },
          {
            path: '/repairmentList',
            name: '??????????????????',
            component: repairmentList,
            meta: {requireAuth: true}
          },
          {
            path: '/repairmentAddMaterial',
            name: '*??????????????????',
            component: editorRepairment,
            meta: {requireAuth: true}
          },
          {
            path: '/repairmentDetail',
            name: '*??????????????????',
            component: repairmentDetail,
            meta: {requireAuth: true}
          },
        ]
      },
      {
        path: '/materialManage',
        name: '????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/materialList',
            name: '????????????',
            component: materialList,
            meta: {requireAuth: true}
          },
          {
            path: '/addMaterial',
            name: '????????????',
            component: addMaterial,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/carManage',
        name: '????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/car_list',
            name: '????????????',
            component: car_list,
            meta: {requireAuth: true}
          },
          {
            path: '/addCar',
            name: '????????????',
            component: addCar,
            meta: {requireAuth: true}
          },
          {
            path: '/editorCar',
            name: '*????????????',
            component: editorCar,
            meta: {requireAuth: true}
          },
          {
            path: '/showClientCar',
            name: '*??????????????????',
            component: showClientCar,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/clientManage',
        name: '????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/queryClient',
            name: '????????????',
            component: queryClient,
            meta: {requireAuth: true}
          },

          {
            path: '/addClient',
            name: '????????????',
            component: addClient,
            meta: {requireAuth: true}
          },

          {
            path: '/editorClient',
            name: '*????????????',
            component: editorClient,
            meta: {requireAuth: true}
          },
        ]
      },
      {
        path: '/bizmenManage',
        name: '???????????????',
        component: bizmenManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/queryBizmen',
            name: '???????????????',
            component: queryBizmen,
            meta: {requireAuth: true}
          },
          {
            path: '/addBizmen',
            name: '???????????????',
            component: addBizmen,
            meta: {requireAuth: true}
          },
          {
            path: '/editorBizmen',
            name: '*???????????????',
            component: editorBizmen,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/workerManage',
        name: '???????????????',
        component: workerManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/queryWorker',
            name: '???????????????',
            component: queryWorker,
            meta: {requireAuth: true}
          },
          {
            path: '/addWorker',
            name: '???????????????',
            component: addWorker,
            meta: {requireAuth: true},
          },
          {
            path: '/editorWorker',
            name: '*???????????????',
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
            name: '???????????????',
            component: workerHome,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/workerTask',
        name: '??????????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/taskFinish',
            name: '??????????????????',
            component: unFinish,
            meta: {requireAuth: true},
          },
          {
            path: '/taskUnFinish',
            name: '????????????',
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
            name: '????????????',
            component: clientHome,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/carIndex',
        name: '????????????',
        component: carIndex,
        meta: {requireAuth: true},
        children: [
          {
            path: '/carList',
            name: '??????????????????',
            component: carList,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/commFinder',
        name: '????????????',
        component: clientManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/commFind',
            name: '????????????',
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
  session ?????????
    1. token
    2. name
    3. type
    4. id
 */
router.beforeEach((to, from, next) => {
  console.log(from.path + ' ====> ' + to.path)
  if (to.meta.requireAuth) { // ???????????????????????????????????????
    if (sessionStorage.getItem("token") === 'true') { // ????????????????????????token
      next()
    } else {
      // ?????????,?????????????????????
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    // ????????????????????????????????????????????????????????????????????????
    if(sessionStorage.getItem("token") === 'true'){
      console.log('???????????????????????????????????????????????????')
      const t = sessionStorage.getItem("type")
      next('/' + t);
    }else{
      next();
    }
  }
});