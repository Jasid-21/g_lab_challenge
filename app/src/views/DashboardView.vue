<template>
  <h1>Hello from dashboard!!</h1>
</template>

<script>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router';

export default {
  name: 'Dashboard',
  setup() {
    const router = useRouter();
    onMounted(async () => {
      const token = localStorage.getItem('jwt');
      const url = 'http://localhost:8080/auth/test';
      const resp = await fetch(url, {
        method: 'get',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + token,
        }
      });
      
      if (resp.status == 200) {
        const text = await resp.json();
        if (!text) {
          router.push('/login');
        }
      }
    });
  }
}
</script>

<style scoped>

</style>