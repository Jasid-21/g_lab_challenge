<template>
  <nav>
    <router-link to="/">Home</router-link>
    <router-link to="/dashboard">Dashboard</router-link>
  </nav>
</template>

<script>
export default {
  name: 'NavbarVue'
}
</script>

<style scoped>
nav {
  height: 70px;
  padding: 0px 30px;
  background-color: rgb(40, 93, 153);
  color: white;

  display: flex;
  justify-content: space-around;
  align-items: center;
}

nav a {
  font-weight: bold;
  color: white;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #86ecbe;
}
</style>