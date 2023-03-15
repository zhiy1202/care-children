<template>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="p1.title" label="标题" width="180" />
      <el-table-column prop="p1.intro" label="简介" width="180" />
      <el-table-column prop="p1.startTime" label="开始时间" />
      <el-table-column prop="p1.endTime" label="结束时间" />
      <el-table-column prop="p2.createTime" label="报名时间" />
    </el-table>
  </template>
  
  <script setup>
import { ref } from "vue";

  const tableData = ref([])
  let getData = async ()=>{
    let result = await $axios.get('/front/activity/my')
    console.log(result.data.data);
    tableData.value = result.data.data
  }
  getData()
  </script>
  
  <style scoped>
  
  </style>