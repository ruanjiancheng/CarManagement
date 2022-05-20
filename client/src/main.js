import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

Vue.config.productionTip = false
Vue.prototype.$url = 'https://sscdemo.mynatapp.cc';
Vue.prototype.$workerType = [' ', '电工', '油漆工']
Vue.prototype.$classification = ['小修', '中修', '大修']
Vue.prototype.$type = ['普通', '加急']
Vue.prototype.$settlementMode = ['自付', '三包', '进保']
Vue.prototype.$dict = ['未创建工单', '进行中', '已完成', '已付款']
Vue.prototype.$orderDict = ['未开始', '进行中', '全部完成', '异常终止']

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
