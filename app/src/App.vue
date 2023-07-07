<template>
  <router-view/>
</template>

<script>
import NavbarVue from './components/NavbarVue.vue';
export default {
  name: 'App',
  components: {
    NavbarVue
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
