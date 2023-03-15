<template>
  <div>
    <!-- top -->
    <div>
      <el-button @click="goLoveAdd" type="primary" round>新增</el-button>
    </div>
    <!-- bottom -->
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="标题" prop="p1.title" />
        <el-table-column label="简介" prop="p1.intro" />
        <el-table-column align="right">
          <template #header>
            <el-input
              v-model="search"
              size="small"
              placeholder="Type to search"
            />
          </template>
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
  </div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useRouter } from "vue-router";

const User = {
  date: "",
  name: "",
  address: "",
};

const search = ref("");
const router = useRouter();
const goLoveAdd = () => {
  router.push("/admin/index/LoveAdd");
};
const filterTableData = computed(() =>
  tableData.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
);
// const handleEdit = (index, row) => {
//   console.log(index, row);
// };
const handleDelete = (index, row) => {
  console.log(index, row);
  console.log(row.p1.id);
  $axios.delete(`/admin/love/del/${row.p1.id}`);
  getData()
};

const tableData = ref([]);
let getData = async () => {
  let result = await $axios.get("/front/love/all");
  console.log(result.data.data);
  tableData.value = result.data.data;
};
getData();
</script>

<style scoped>
</style>