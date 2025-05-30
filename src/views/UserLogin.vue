<template>
  <div class="login">
    <h1>用户登录</h1>
    <form @submit.prevent="login">
      <div>
        <label>用户名：</label>
        <input v-model="username" type="text" required />
      </div>
      <div>
        <label>密码：</label>
        <input v-model="password" type="password" required />
      </div>
      <button type="submit">登录</button>
    </form>

    <!-- 注册按钮，用于跳转到注册页面 -->
    <p>还没有账号？</p>
    <router-link to="/register">
      <button class="register-button">注册</button>
    </router-link>
  </div>
</template>

<script>
import API from "../api/config";

export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    async login() {
      try {
        const response = await API.post("/auth/login", {
          username: this.username,
          password: this.password,
        });
        alert(response.data); // 显示登录成功信息
        // 登录跳转，可以根据角色跳转到不同页面
        this.$router.push("/dashboard");
      } catch (error) {
        console.error("Error during login:", error.response.data);
        alert("登录失败：" + error.response.data);
      }
    },
  },
};
</script>

<style scoped>
.login {
  width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
label {
  display: block;
  margin-bottom: 5px;
}
input {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
button {
  padding: 10px 15px;
  background-color: #5cb85c;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background-color: #4cae4c;
}
</style>