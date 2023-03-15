<template>
  <div class="love-list">
    <ul>
      <li class="love-item" v-for="item in loveList" :key="item.p1.id">
        <div class="love-item-bar">
          <!-- 图片 -->
          <div class="love-bar-img">
            <img :src="item.p2.imgPath" alt="网络错误" />
          </div>
          <!-- 介绍 -->
          <div class="love-bar-info">
            <div>标题：{{ item.p1.title }}</div>
            <div>简介：{{ item.p1.intro }}</div>
            <div>时间： {{ item.p1.createTime }}</div>
            <div @click="goLoveDetail(item.p1.id)">详情</div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const goLoveDetail = (id) => {
  console.log(id);
  router.push({ path: "/index/loveDetail", query: { id:id } });
};
var loveList = ref([]);
const getAllLove = async () => {
  let result = await $axios.get("/front/love/all");
  loveList.value = result.data.data;
  console.log(loveList);
};
getAllLove();
</script>

<style scoped>
.love-list {
  width: 100%;
  height: 200px;
}
.love-item {
  width: 100%;
  height: 100%;
}
.love-item-bar {
  display: flex;
  width: 80%;
  height: 200px;
}
.love-bar-img {
  flex: 1;
  background-color: red;
}
.love-bar-info {
  flex: 4;
  margin-left: 40px;
  background-color: skyblue;
}
img {
  width: 100%;
  height: 100%;
}
</style>