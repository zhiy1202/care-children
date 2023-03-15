<template>
    <div>
      <el-form
        ref="ruleFormRef"
        :model="donate"
        status-icon
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="邮递编号" prop="checkPass">
          <el-input
            v-model="donate.trackingNumber"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="捐赠物品" prop="checkPass">
          <el-input
            v-model="donate.content"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addDonate">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
    
    <script setup>
  import { reactive } from "vue";
  
  const donate = reactive({
    trackingNumber:'',
    content:''
  })
let addDonate = async ()=>{
    let formData = new FormData()
    formData.append('trackingNumber',donate.trackingNumber)
    formData.append('content',donate.content)
    let result = await $axios.post(`/front/donate/addForMy`,formData)
    if (result.data.data) {
        alert('捐赠成功 感谢捐赠')
    }
}
  </script>
    
    <style scoped>
  </style>