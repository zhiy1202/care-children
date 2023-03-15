import {createRouter,createWebHashHistory} from 'vue-router';



const routes = [
    {
        path:'/',
        component: ()=> import('../views/front/Login.vue')
    },
    {
        path:'/register',
        component: ()=> import('../views/front/Register.vue')
    },
    {
        path:'/index',
        component: ()=> import('../views/front/Index.vue'),
        children:[
            {
                path:'lovelist',
                component: ()=>import('../components/front/LoveThingList.vue')
            },
            {
                path:'loveDetail',
                component: ()=>import('../components/front/LoveThingDetail.vue')
            },
            {
                path:'lovePeople',
                component: ()=>import('../components/front/LovePeople.vue')
            },
            {
                path:'childNeed',
                component: ()=>import('../components/front/ChildNeed.vue')
            },
            {
                path:'ChildDogateByMe',
                component: ()=>import('../components/front/ChildDogateByMe.vue')
            },
            {
                path:'ChildDogateUse',
                component: ()=>import('../components/front/ChildDogateUse.vue')
            },
            {
                path:'ActivityMy',
                component: ()=>import('../components/front/ActivityMy.vue')
            },
            {
                path:'ActivityStudy/:id',
                component: ()=>import('../components/front/ActivityStudy.vue')
            },
            {
                path:'UserInfo',
                component: ()=>import('../components/front/UserInfo.vue')
            },
            {
                path:'DonateAdd',
                component: ()=>import('../components/front/ChildDonateAdd.vue')
            },

        ]
    },
    {
        path:'/admin',
        component: ()=> import('../views/admin/Login.vue'),
    },
    {
        path:'/admin/register',
        component: ()=> import('../views/admin/Register.vue'),
    },
    {
        path:'/admin/index',
        component: ()=> import('../views/admin/Index.vue'),
        children:[
            {
                path:'/admin/index/LoveAdd',
                component : ()=> import('../components/admin/LoveAdd.vue')
            },
            {
                path:'/admin/index/LoveNews',
                component : ()=> import('../components/admin/LoveNews.vue')
            },
            {
                path:'/admin/index/DonateAdd',
                component : ()=> import('../components/admin/DonateAdd.vue')
            },
            {
                path:'/admin/index/DonateReady',
                component : ()=> import('../components/admin/DonateReady.vue')
            },
            {
                path:'/admin/index/DonateComplete',
                component : ()=> import('../components/admin/DonateComplete.vue')
            },
            {
                path:'/admin/index/ActivityAdd',
                component : ()=> import('../components/admin/ActivityAdd.vue')
            },
            {
                path:'/admin/index/ActivityReady',
                component : ()=> import('../components/admin/ActivityReady.vue')
            },
            {
                path:'/admin/index/ActivityComplete',
                component : ()=> import('../components/admin/ActivityComplete.vue')
            },
            {
                path:'/admin/index/UserManage',
                component : ()=> import('../components/admin/UserManage.vue')
            }
        ]
    },
]
const router = createRouter(
    {
        history: createWebHashHistory(),
        routes: routes
    }
)

export default router