<template>
  <div>
    <el-form :model="form" label-width="120px">
      <el-form-item label="标题">
        <el-input v-model="form.title" />
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.intro" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="form.content" type="textarea" />
      </el-form-item>
      <div>活动类型</div>
      <el-select
        v-model="form.activityType"
        class="m-2"
        placeholder="活动类型"
        size="large"
      >
        <el-option
          v-for="item in form.activityTypeList"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>

      <div class="block">
        <span class="demonstration">活动时间</span>
        <el-date-picker
          v-model="value1"
          type="daterange"
          range-separator="To"
          start-placeholder="Start date"
          end-placeholder="End date"
          :size="size"
          value-format="YYYY-MM-DD"
        />
      </div>

      <el-upload
        v-model:file-list="fileList"
        class="upload-demo"
        action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        list-type="picture"
        multiple
        :before-upload="beforeUpload"
        :http-request="upload"
      >
        <el-button type="primary">图片上传</el-button>
        <template #tip> </template>
      </el-upload>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button @click="a">a</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
  
  <script setup>
import { reactive, ref } from "vue";

const size = ref<'default' | 'large' | 'small'>('default')
const value1 = ref('')

const a = ()=>{
  console.log(value1);
}

const handlePreview = (file) => {
  console.log(file);
};

const beforeUpload = (file) => {
  form.files.push(file);
};
const handleRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
};

const upload = (file) => {
  console.log(file);
};
// do not use same name with ref
const form = reactive({
  title: "",
  intro: "",
  activityType: 1,
  activityTypeList: [
    {
      value: 1,
      label: "学习互助",
    },
    {
      value: 2,
      label: "亲子活动",
    },
    {
      value: 3,
      label: "其他",
    },
  ],
  content: "",
  files: [],
});

const onSubmit = async () => {
  console.log(form.files);
  let formData = new FormData();
  formData.append("title", form.title);
  formData.append("intro", form.intro);
  formData.append("childNeed", form.childNeed);
  formData.append("content", form.content);
  form.files.forEach((item) => {
    formData.append("files", item);
  });
  formData.append('activityType',form.activityType)
  formData.append('startTime',value1.value[0])
  formData.append('endTime',value1.value[1])

  let result = await $axios.post("/admin/activity/add", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  if (result.data.data) {
    alert("上传成功");
  }
};
</script>
  
  <style scoped>
</style>