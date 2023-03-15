<template>
  <div>
    <el-form
    :model="user"
    status-icon
    label-width="120px"
    class="demo-ruleForm"
  >
    <el-form-item label="用户名" prop="pass">
      <el-input v-model="user.username" type="text" autocomplete="off" />
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input v-model="user.password" type="password" autocomplete="off" />
    </el-form-item>
    <el-form-item label="手机号" prop="pass">
      <el-input v-model="user.phone" type="手机号" autocomplete="off" />
    </el-form-item>
    <el-form-item label="生日" prop="age">
      <el-date-picker
        v-model="user.birthday"
        type="date"
        placeholder="生日"
        :size="size"
        value-format="YYYY-MM-DD"
      />
    </el-form-item>
    <el-form-item>
      <el-button @click="update(user.id)">更新</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>

<script setup>
import { ref } from "vue";

const user = ref({
    username:'',
    password:'',
    birthday:{},
    phone:''
})

const update = async ()=>{
  console.log(user);
  let fromData = new FormData()
  fromData.append("username",user.value.username)
  fromData.append("password",user.value.password)
  fromData.append("birthday",user.value.birthday)
  fromData.append("phone",user.value.phone)
  let result = await $axios.post('/front/user/upload',fromData)
  console.log(result);
  if (result.data.data) {
    alert('更新成功')
    getData()
  }
}

let getData = async ()=>{
  let result = await $axios('/front/user/Info')
  user.value = result.data.data
}
getData()
</script>

<style scoped>

</style>