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
            <span style="margin-left: 10px">{{ scope.row.trackingNumber }}</span>
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
            <!-- <template #default>
              <div>name: {{ scope.row.trackingNumber }}</div>
              <div>address: {{ scope.row.trackingNumber }}</div>
            </template> -->
            <template #reference>
              <el-tag>{{ scope.row.content }}</el-tag>
            </template>
          </el-popover>
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
            >签收</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
  
  <script setup>
  import { Timer } from '@element-plus/icons-vue'
import { ref } from 'vue'

const donate = {
  date: '',
  name: '',
  address: ''
}

const handleEdit = (index, row) => {
  console.log(index, row)
}
const handleDelete = async (index, row) => {
  console.log(index, row)
  console.log(row.id);
  let result = await $axios.put(`/admin/donate/update/${row.id}`)
  getData()
}

const tableData = ref([]);
let getData = async () => {
  let result = await $axios.get("/admin/donate/ready");
  console.log(result.data.data);
  tableData.value = result.data.data;
};
getData();
</script>
  
  <style scoped>
</style>