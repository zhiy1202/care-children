<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="活动" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.p2.title }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="参加人员" width="180">
        <template #default="scope">
          <el-popover
            effect="light"
            trigger="hover"
            placement="top"
            width="auto"
          >
            <template #default>
              <div>手机号: {{ scope.row.p3.phone }}</div>
              <div>生日: {{ scope.row.p3.birthday }}</div>
            </template>
            <template #reference>
              <el-tag>{{ scope.row.p3.username }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="报名时间">
        <template #default="scope">
          <div>{{ scope.row.p1.createTime }}</div>
        </template>
      </el-table-column>
      <el-table-column label="Operations">
        <template #default="scope">
          <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >Edit</el-button
          > -->
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
const handleDelete = async (index, row) => {
  console.log(index, row);
  let result = await $axios.delete(`/admin/activity/delJoin/${row.p1.id}`)
  console.log(result);
  getData()
};

const tableData = ref([]);
let getData = async () => {
  let result = await $axios.get("/admin/activity/query");
  console.log(result.data.data);
  tableData.value = result.data.data;
};
getData();
</script>
  
  <style scoped>
</style>