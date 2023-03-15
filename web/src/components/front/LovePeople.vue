<template>
    <div class="love-list">
      <ul>
          <li class="love-item">
              <div class="love-item-bar">
                  <!-- 图片 -->
                  <div class="love-bar-img"></div>
                  <!-- 介绍 -->
                  <div class="love-bar-info">
                      <div>标题</div>
                      <div>简介</div>
                      <div>捐赠物品</div>
                      <div>捐赠时间</div>
                  </div>
              </div>
          </li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { useRouter } from "vue-router"
  
  const router = useRouter()
  const goLoveDetail = ()=>{
      router.push('/index/loveDetail')
  }
  </script>
  
  <style scoped>
  .love-list{
      width: 100%;
      height: 200px;
  }
  .love-item{
      width: 100%;
      height: 100%;
  }
  .love-item-bar{
      display: flex;
      width: 80%;
      height: 200px;
  }
  .love-bar-img{
      flex: 1;
      background-color: red;
  }
  .love-bar-info{
      flex: 4;
      background-color: skyblue;
  }
  </style>