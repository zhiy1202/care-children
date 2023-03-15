<template>
    <div class="love-list">
      <ul>
          <li class="love-item" v-for="item,index in tableData" :key="index">
              <div class="love-item-bar">
                  <!-- 图片 -->
                  <div class="love-bar-img">
                    <img :src="item.p2.activityImg" alt="">
                  </div>
                  <!-- 介绍 -->
                  <div class="love-bar-info">
                      <div>标题:{{ item.p1.title }}</div>
                      <div>简介:{{ item.p1.intro }}</div>
                      <div>开始时间:{{ item.p1.startTime }}</div>
                      <div>结束时间:{{ item.p1.endTime }}</div>
                      <div @click="goLoveDetail(item.p1.id)">我要报名</div>
                  </div>
              </div>
          </li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue"
import { onBeforeRouteUpdate, useRoute, useRouter } from "vue-router"
  
  const router = useRouter()
  const goLoveDetail = async (id)=>{
    console.log(id);
    let result = await $axios.post(`/front/activity/apply/${id}`)
    if (result.data.data) {
      alert('报名成功')
    }
      // router.push('/index/loveDetail')
  }
  let tableData = ref([])
  let getData = async (type)=>{
    let result = await $axios.get(`/front/activity/all/${type}`)
    console.log(result.data);
    tableData.value = result.data.data
  }
  onBeforeRouteUpdate((to)=>{
    let a = to.path
    let arr = a.split("/")
    let type = arr[arr.length-1]
    getData(type)
  })



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
  img{
    width: 100%;
    height: 100%;
  }
  </style>