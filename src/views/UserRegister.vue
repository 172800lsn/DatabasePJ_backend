<template>
  <div class="register">
    <h1>用户注册</h1>
    <form @submit.prevent="register">
      <div>
        <label>用户名：</label>
        <input v-model="user.username" type="text" required />
      </div>
      <div>
        <label>密码：</label>
        <input v-model="user.password" type="password" required />
      </div>
      <div>
        <label>邮箱：</label>
        <input v-model="user.email" type="email" />
      </div>
      <div>
        <label>用户角色：</label>
        <select v-model="user.role">
          <option value="USER">普通用户</option>
          <option value="WORKER">维修工</option>
          <option value="ADMIN">管理员</option>
        </select>
      </div>
      <button type="submit">注册</button>
    </form>
  </div>
</template>

<script>
import API from "../api/config";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
        email: "",
        role: "USER", // 默认角色
      },
    };
  },
  methods: {
    async register() {
      try {
        const response = await API.post("/auth/register", this.user);
        console.log("response", response); // 输出返回内容
        const message = response?.data??"注册成功";
        alert(message); // 显示成功信息
        this.$router.push("/login"); // 跳转到登录页面
      } catch (error) {
        console.error("Error during registration:", error);
        alert("用户注册失败：" + (error.response?.data || "未知错误，请检查网络或后端。"));
      }
    },
  },
};
</script>

<style scoped>
.register {
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
input, select {
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