<template>
  <nav>
    <router-link to="/">Home</router-link>
  </nav>
</template>

<script>
export default {
  name: 'NavbarVue'
}
</script>

<style scoped>
nav {
  padding: 30px;
  background-color: rgb(40, 93, 153);
  color: white;
}

nav a {
  font-weight: bold;
  color: white;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>