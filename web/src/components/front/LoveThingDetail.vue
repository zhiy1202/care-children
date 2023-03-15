<template>
  <div class="love-detail">
    <!-- 上部分 -->
    <div class="love-top">
      <!-- 图片 -->
      <div class="left">
        <el-carousel :interval="5000" arrow="always">
          <el-carousel-item v-for="item,index in loveDetail.p2" :key="index">
            <!-- <h3 text="2xl" justify="center">{{ item }}</h3> -->
            <img :src="item.imgPath" alt="图片加载失败">
          </el-carousel-item>
        </el-carousel>
      </div>
      <!-- 信息 -->
      <div class="right">
        <p>标题：{{ loveDetail.p1.title }}</p>
        <p>简介： {{ loveDetail.p1.intro }}</p>
        <p>发布时间： {{ loveDetail.p1.createTime }}</p>
        <p>
          <el-button type="danger" @click="addDogate(loveDetail.p1.id)">捐赠</el-button>
        </p>
      </div>
    </div>
    <!-- 下部分 -->
    <div class="love-bottom">
      <!-- 内容 -->
      <div class="left">
        <p>内容：</p>
        {{ loveDetail.p1.content }}
      </div>
      <!-- 所需物品 -->
      <div class="right">
        <p>孩子所需：</p>
        {{ loveDetail.p1.childNeed }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
let router = useRouter()
let addDogate = (id)=>{
  router.push('/index/DonateAdd')
}

let loveDetail = ref({});
let getLoveDetail = () => {
  let route = useRoute();
  let { id } = route.query;
  console.log(id);
  $axios.get(`/front/love/detail/${id}`).then((res) => {
    console.log(res.data);
    loveDetail.value = res.data.data;
  });
};
getLoveDetail();
</script>

<style scoped>
.love-detail {
  width: 100%;
}
.left,
.right {
  flex: 1;
}
.love-top {
  display: flex;
  height: 300px;
  background-color: orange;
}

.love-bottom {
  display: flex;
  height: 500px;
  background-color: skyblue;
}
img{
  height: 100%;
  width: 100%;
}
</style>