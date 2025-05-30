import axios from 'axios';

const API = axios.create({
    baseURL: 'http://localhost:8080/api', // 后端地址
    headers: {
        'Content-Type': 'application/json',
    },
});

export default API;