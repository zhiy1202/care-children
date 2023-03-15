<template>
  <div class="index-container">
    <el-container>
      <el-header class="header">
        <el-carousel :interval="5000" arrow="always">
          <el-carousel-item v-for="item,index in tableData" :key="index">
            <!-- <h3 text="2xl" justify="center">{{ item }}</h3> -->
            <img :src="item.path" alt="图片加载失败">
          </el-carousel-item>
        </el-carousel>
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
            :router="true"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>爱心关怀</span>
              </template>
              <el-menu-item-group>
                <template #title><span>新闻</span></template>
                <el-menu-item index="/index/lovelist">关怀新闻</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="留守儿童问题">
                <!-- <el-menu-item index="/index/lovePeople">留守儿童关怀案例</el-menu-item> -->
                <!-- <el-menu-item index="1-3">留守儿童问题科普</el-menu-item> -->
              </el-menu-item-group>
            </el-sub-menu>

            <el-sub-menu index="2">
              <template #title>
                <el-icon><location /></el-icon>
                <span>我要捐赠</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/index/childNeed">孩子所需</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="我的捐赠">
                <el-menu-item index="/index/ChildDogateByMe">我捐赠的</el-menu-item>
                <el-menu-item index="/index/ChildDogateUse">物品使用详情</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-sub-menu index="3">
              <template #title>
                <el-icon><document /></el-icon>
                <span>志愿活动</span>
              </template>
              <el-menu-item-group>
                <!-- <template #title><span>孩子所需</span></template> -->
                <el-menu-item index="/index/ActivityMy">我的报名</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="活动类型">
                <el-menu-item index="/index/ActivityStudy/1">学习互助</el-menu-item>
                <el-menu-item index="/index/ActivityStudy/2">亲子陪伴</el-menu-item>
                <el-menu-item index="/index/ActivityStudy/3">其他</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-menu-item index="/index/UserInfo">
              <el-icon><setting /></el-icon>
              <template #title>个人信息</template>
            </el-menu-item>
            <el-menu-item @click="exit" index="/">
              <el-icon><setting /></el-icon>
              <template #title>退出登录</template>
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
import router from "@/router";

const isCollapse = ref(true);
const handleOpen = (key, keyPath) => {
  console.log(key, keyPath);
};
const handleClose = (key, keyPath) => {
  console.log(key, keyPath);
};
let tableData = ref([])
let getData = async ()=>{
  let result =await $axios.get('/admin/img/get')
  console.log(result.data);
  tableData.value = result.data.data
}
const exit = async ()=>{
  let result = await $axios.get("/front/user/logout")
  console.log(result);
  if (result.data.data) {
    alert('安全退出')
    router.push('/')
  }
}
getData()
</script>

<style scoped>
.index-container {
  width: 100%;
}
.header {
  height: 300px;
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
img{
  width: 100%;
  height: 100%;
}
</style>