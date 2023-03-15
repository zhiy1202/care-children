<template>
  <div>
    <el-form
      ref="ruleFormRef"
      :model="donate"
      status-icon
      label-width="120px"
      class="demo-ruleForm"
    >
      <!-- <el-form-item label="标题" prop="pass">
        <el-input v-model="donate.title" type="text" autocomplete="off" />
      </el-form-item> -->
      <el-form-item label="所需物品" prop="checkPass">
        <el-input
          v-model="donate.content"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" @click="getData">提交</el-button>
        <el-button >重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
  
  <script setup>
import { reactive } from "vue";

const donate = reactive({
  title:'',
  content:''
})
let getData = async () => {
  let formData = new FormData()
  formData.append('content',donate.content)
  let result = await $axios.post("/admin/donate/add",formData);
  console.log(result.data.data);
  donate = result.data.data;
};
</script>
  
  <style scoped>
</style>