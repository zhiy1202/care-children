<template>
    <div class="index-container">
      <el-container>
        <el-header class="header">
          <div>
            <h1>关爱儿童控制台</h1>
          </div>
        </el-header>
        <el-container>
          <el-aside class="aside">
            <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
              <el-radio-button :label="false">展开</el-radio-button>
              <el-radio-button :label="true">收起</el-radio-button>
            </el-radio-group>
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              :collapse="isCollapse"
              @open="handleOpen"
              @close="handleClose"
              router="true"
            >
              <el-sub-menu index="1">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>爱心关怀管理</span>
                </template>
                <el-menu-item-group>
                  <template #title><span>新闻</span></template>
                  <el-menu-item index="/admin/index/LoveNews">关怀新闻管理</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="留守儿童问题">
                  <el-menu-item index="/admin/index/LoveAdd">关怀案例发布</el-menu-item>
                  <!-- <el-menu-item index="1-3">留守儿童问题科普</el-menu-item> -->
                </el-menu-item-group>
              </el-sub-menu>
  
              <el-sub-menu index="2">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>捐赠管理</span>
                </template>
                <el-menu-item-group>
                  <!-- <template #title><span>孩子所需</span></template> -->
                  <el-menu-item index="/admin/index/DonateAdd">需求发布</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="用户捐赠">
                  <el-menu-item index="/admin/index/DonateReady">待签收</el-menu-item>
                  <el-menu-item index="/admin/index/DonateComplete">已签收</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
  
              <el-sub-menu index="3">
                <template #title>
                  <el-icon><document /></el-icon>
                  <span>志愿活动管理</span>
                </template>
                <el-menu-item-group>
                  <!-- <template #title><span>孩子所需</span></template> -->
                  <el-menu-item index="/admin/index/ActivityAdd">发布活动</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="活动详情">
                  <el-menu-item index="/admin/index/ActivityReady">报名人员</el-menu-item>
                  <!-- <el-menu-item index="/admin/index/ActivityComplete">确定人员</el-menu-item> -->
                </el-menu-item-group>
              </el-sub-menu>
  
              <el-menu-item index="/admin/index/UserManage">
                <el-icon><setting /></el-icon>
                <template #title>用户管理</template>
              </el-menu-item>
              <el-menu-item @click="exit" index="/">
                <el-icon><setting /></el-icon>
                <template #title>退出系统</template>
              </el-menu-item>
            </el-menu>
          </el-aside>
  
          <el-main>
              <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
  } from "@element-plus/icons-vue";
  
  const isCollapse = ref(true);
  const handleOpen = (key, keyPath) => {
    console.log(key, keyPath);
  };
  const handleClose = (key, keyPath) => {
    console.log(key, keyPath);
  };

  const exit = async ()=>{
  let result = await $axios.get("/front/user/logout")
  if (result.data.data) {
    alert('安全退出')
    router.push('/admin')
  }
}
  </script>
  
  <style scoped>
  .index-container {
    width: 100%;
  }
  .header {
    height: 100px;
    background-color: gray;
    text-align: center;
  }
  .header div{
    position: relative;
    top: 30%;
  }
  .aside {
    width: 200px;
    height: 900px;
  }
  
  .el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
    text-align: center;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  </style>