<template>
    <div class="container">
      <div class="login">
        <!-- <h1>登录</h1> -->
        <el-form
          :model="user"
          status-icon
          label-width="120px"
          class="demo-ruleForm"
        >
          <el-form-item label="用户名" prop="pass">
            <el-input
              v-model="user.username"
              type="password"
              autocomplete="off"
              placeholder="请输入用户名"
            />
          </el-form-item>
          <el-form-item label="密码" prop="checkPass">
            <el-input
              v-model="user.password"
              type="password"
              autocomplete="off"
              placeholder="请输入密码"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="register">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { onMounted, reactive, ref} from "vue";
  import { useRouter } from "vue-router";
  
  const router = useRouter()
  onMounted(() => {
    let width = window.innerWidth;
    let height = window.innerHeight;
    let bar = document.querySelector(".container");
    bar.style.width = width + "px";
    bar.style.height = height + "px";
  });
  const user = reactive({
    username: "",
    password: "",
  });
  
  const register = ()=>{
    console.log(user.username,user.password);
    let formData = new FormData()
    formData.append("username",user.username)
    formData.append('password',user.password)
      window.$axios.post('/admin/user/register',formData).then(res=>{
      let result = res.data
      if(result.data){
        router.push('/admin')
        alert("注册成功 请登录")
      }else{
        alert('用户存在')
      }
      
    })
  }
  </script>
  
  <style scoped>
  .container {
    background-image: url("../../assets/1.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
  }
  .login {
    width: 40%;
    height: 500px;
    margin: 0 auto;
    position: relative;
    top: 50%;
    text-align: center;
    color: red;
  }
  </style>