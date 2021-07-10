
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderInfoManager from "./components/OrderInfoManager"

import StockInfoManager from "./components/StockInfoManager"

import PaymentInfoManager from "./components/PaymentInfoManager"


import Dashboard from "./components/Dashboard"
import RentInfoManager from "./components/RentInfoManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orderInfos',
                name: 'OrderInfoManager',
                component: OrderInfoManager
            },

            {
                path: '/stockInfos',
                name: 'StockInfoManager',
                component: StockInfoManager
            },

            {
                path: '/paymentInfos',
                name: 'PaymentInfoManager',
                component: PaymentInfoManager
            },


            {
                path: '/dashboards',
                name: 'Dashboard',
                component: Dashboard
            },
            {
                path: '/rentInfos',
                name: 'RentInfoManager',
                component: RentInfoManager
            },



    ]
})
