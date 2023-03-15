<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="时间" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="快递单号" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{
              scope.row.trackingNumber
            }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="捐赠内容" width="180">
        <template #default="scope">
          <el-popover
            effect="light"
            trigger="hover"
            placement="top"
            width="auto"
          >
            <template #reference>
              <el-tag>{{ scope.row.content }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="使用去向">
        <template #default="scope">
          <input type="text" v-model="scope.row.go" />
        </template>
      </el-table-column>
      <el-table-column label="Operations">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >修改使用去向</el-button
          >
          <!-- <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          > -->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
  
  <script setup>
import { Timer } from "@element-plus/icons-vue";
import { ref } from "vue";

const handleEdit = async (index, row) => {
  console.log(index, row);
  let a = row.go
  console.log(a);
  let formData = new FormData();
  formData.append("content", a);
  let result = await $axios.post(`/admin/donate/addUse/${row.id}`, formData);
  if (result.data.data) {
    alert('修改用处成功')
  }
};

const tableData = ref([]);
let getData = async () => {
  let result = await $axios.get("/admin/donate/complete");
  console.log(result.data.data);
  tableData.value = result.data.data;
};
getData();
</script>
  
  <style scoped>
</style>