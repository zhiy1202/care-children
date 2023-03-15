<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="用户id" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.id }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="用户名" width="180">
        <template #default="scope">
          <el-popover
            effect="light"
            trigger="hover"
            placement="top"
            width="auto"
          >
            <template #default>
              <div>name: {{ scope.row.name }}</div>
              <div>address: {{ scope.row.address }}</div>
            </template>
            <template #reference>
              <el-tag>{{ scope.row.username }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="手机号码">
        <template #default="scope">
          <div>{{ scope.row.phone }}</div>
        </template>
      </el-table-column>
      <el-table-column label="Operations">
        <template #default="scope">
          <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >使用去向</el-button> -->
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
  
  <script setup>
import { Timer } from "@element-plus/icons-vue";
import { ref } from "vue";

const handleEdit = (index, row) => {
  console.log(index, row);
};
const handleDelete = (index, row) => {
  console.log(index, row);
  $axios.delete(`/admin/user/delUserById?userId=${row.id}`)
  getData()
};
const tableData = ref([]);
let getData = async () => {
  let result = await $axios.get("/admin/user/allUsers");
  console.log(result.data.data);
  tableData.value = result.data.data;
};
getData();
</script>
  
  <style scoped>
</style>


