<template>
  <div class="love-list">
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="需求编号" width="180" />
      <el-table-column prop="content" label="所需" width="180" />
      <el-table-column prop="content" label="操作">
        <template #default="scope">
          <el-button
            type="primary"
            size="small"
            @click.prevent="addDonate(scope.row)"
          >
            捐赠
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
  
  <script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
let router = useRouter()
let addDonate = (scope)=>{
    console.log(scope);
    router.push('/index/DonateAdd')
}
var tableData = ref([]);
let getChildNeed = async () => {
  let result = await $axios.get("/front/donate/childNeed");
  tableData.value = result.data.data;
};
getChildNeed();
</script>
  
  <style scoped>
.love-list {
  width: 100%;
  height: 200px;
}
.love-item {
  width: 100%;
  height: 100%;
}
.love-item-bar {
  display: flex;
  width: 80%;
  height: 200px;
}
.love-bar-img {
  flex: 1;
  background-color: red;
}
.love-bar-info {
  flex: 4;
  background-color: skyblue;
}
img {
  width: 100%;
  height: 100%;
}
</style>