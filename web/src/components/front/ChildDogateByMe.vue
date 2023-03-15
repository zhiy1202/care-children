<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="p2.title" label="标题" width="180" />
    <el-table-column prop="p1.content" label="捐赠内容" width="180" />
    <el-table-column prop="p1.trackingNumber" label="快递单号" width="180" />
    <el-table-column prop="p1.createTime" label="捐赠时间" />
  </el-table>
</template>

<script setup>
import { ref } from "vue";

const tableData = ref([])
let donateByMe = async ()=>{
  let result = await $axios.get('/front/donate/donateForMe')
  console.log(result.data.data);
  tableData.value = result.data.data
}
donateByMe()
</script>

<style scoped>

</style>