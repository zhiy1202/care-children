<template>
  <div>
    <el-form :model="form" label-width="120px">
      <el-form-item label="标题">
        <el-input v-model="form.title" />
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.intro" />
      </el-form-item>
      <el-form-item label="孩子所需">
        <el-input v-model="form.childNeed" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="form.content" type="textarea" />
      </el-form-item>

      <el-upload
        v-model:file-list="fileList"
        class="upload-demo"
        action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
        :on-remove="handleRemove"
        list-type="picture"
        multiple
        :before-upload="beforeUpload"
        :http-request="upload"
      >
        <el-button type="primary">资源上传</el-button>
        <template #tip> </template>
      </el-upload>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button>重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
  
  <script setup>
import { reactive } from "vue";
import { ref } from 'vue'

const form = reactive({
  title: "",
  intro: "",
  childNeed: "",
  content: "",
  files: [],
});

const fileList = ref([])
const beforeUpload = (file)=>{
  form.files.push(file)
}
const handleRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}

const upload = (file)=>{
  console.log(file);
}

const onSubmit = async () => {
  console.log(form.files);
  let formData = new FormData()
  formData.append('title',form.title)
  formData.append('intro',form.intro)
  formData.append('childNeed',form.childNeed)
  formData.append('content',form.content)
  form.files.forEach((item)=>{
    formData.append('files',item)
  })
  
  let result = await $axios.post('/admin/love/add',formData,{
    headers:{
      'Content-Type': 'multipart/form-data'
    }
  })
  if (result.data.data) {
    alert('上传成功')
  }
};
</script>
  
  <style scoped>
</style>