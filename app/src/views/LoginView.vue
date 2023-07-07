<template>
  <div class="master-container">
    <navbar-vue></navbar-vue>
    <div class="body">
      <div class="form-container">
        <h3>Login Form</h3>
        <form class="login-form" @submit.prevent="login">
          <label for="email">Email</label><br>
          <input type="email" id="email" v-model="email" placeholder="Ej: alguien@gmail.com">
          <br><br>
          <label for="password">Password</label><br>
          <input type="password" id="password" v-model="password" placeholder="Escriba su contraseña">
          <br><br>
          <input type="submit" value="Login!">
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarVue from '@/components/NavbarVue.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
export default {
  name: 'LoginView',
  components: {
    NavbarVue
  },
  setup() {
    const router = useRouter();
    const email = ref('admin@reserve.com');
    const password = ref('admin_password');

    const login = async () => {
      const url = 'http://localhost:8080/auth/login';
      const credentials = { email: email.value, password: password.value };
      const resp = await fetch(url, {
        method: 'post',
        body: JSON.stringify(credentials),
        headers: {
          'Content-Type': 'application/json'
        }
      });
      
      if (resp.status == 200) {
        const token = await resp.text();
        localStorage.setItem('jwt', token);
        router.push('/dashboard');
      }
    }

    return { email, password, login }
  }
}
</script>

<style scoped>
* {
  -moz-box-sizing:border-box;
  -webkit-box-sizing:border-box;
  box-sizing:border-box;
}
.master-container {
  width: 100vw;
  height: 100vh;

  display: grid;
  grid-template-rows: 60px calc(100vh - 60px);
}

.body {
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-container {
  width: 90%;
  max-width: 300px;

  border-radius: 10px;
  box-shadow: 0px 0px 3px 1px rgb(51, 51, 51);
  padding: 10px;
  padding-bottom: 30px;
}

.form-container h3 {
  text-align: center;
}

input {
  width: 100%;
  height: 25px;
  color: gray;
  border-radius: 5px;
  border: 1px solid gray;
}

input[type="submit"] {
  height: 30px;
  background-color: rgb(0, 0, 80);
  border: 2px solid rgb(0, 0, 80);
  color: white;
  font-weight: 600;

  transition-property: background-color, color;
  transition-duration: 180ms;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: rgba(0, 0, 80, 0);
  color: rgb(0, 0, 80);
}

label {
  font-size: 15px;
  font-weight: 600;
  font-style: italic;
}
</style>