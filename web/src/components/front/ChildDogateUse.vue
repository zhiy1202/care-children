<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="p3.title" label="标题" width="180" />
    <el-table-column prop="p2.trackingNumber" label="快递单号" />
    <el-table-column prop="p2.content" label="我捐赠的" />
    <el-table-column prop="p1.content" label="用处" />
    <el-table-column prop="p1.createTime" label="时间" width="180" />
  </el-table>
</template>
  
<script setup>
import { ref } from "vue";

let tableData = ref([]);
let getData = async () => {
  let result = await $axios.get("/front/donate/useForMe");
  console.log(result.data.data);
  tableData.value = result.data.data;
}
getData()
</script>
<style scoped>
</style>