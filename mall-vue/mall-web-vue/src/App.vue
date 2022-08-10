<template>
  <div id="app">
    <!-- <canvas id="myCanvas"></canvas> -->
    <div>
      <top-bar></top-bar>
      <main id="aaa">
        <keep-alive>
          <router-view />
        </keep-alive>
      </main>
      <bottom></bottom>
    </div>
  </div>
</template>

<script>
import TopBar from "./components/topbar/TopBar"
import bottom from "./components/bottom/bottom"
export default {
  name: 'App',
  components: {
    TopBar,
    bottom
  },
  mounted () {
    //设置canvas图像为背景
    // document.getElementById('aaa').style.backgroundColor = 'red';
    // document.getElementById('aaa').style.backgroundImage = 'url(' + require('@/assets/img/test.jpg') + ')';
  },
  methods: {

  }
}
</script>

<style>
html {
  position: relative;
  bottom: 0;
  left: 0;
}
body {
  position: relative;
  bottom: 0;
  left: 0;
}
#myCanvas {
  /* background-color: #FEDDE0; */
  width: 100%;
  position: absolute;
  z-index: 10;
}
#aaa {
  position: absolute;
  z-index: 200;
  margin: auto;
  /* background-color: red; */
  background-image: url("./assets/img/bg1.svg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 100%;
  height: calc(100vh);
}
</style>
