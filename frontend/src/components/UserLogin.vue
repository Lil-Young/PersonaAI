<template>
    <v-container>
        <v-row justify="center">
            <v-col md="4">
                <v-card>
                    <v-card-title class="text-h5 text-center">
                        로그인
                    </v-card-title>
                    <v-card-text>
                        <v-form>
                            <v-text-field
                                label="이메일"
                                v-model="email"
                            >

                            </v-text-field>
                            <v-text-field
                                label="패스워드"
                                v-model="password"
                                type="password"
                            >
                            </v-text-field>
                            <v-btn type="button" color="primary" block @click="userLogin()">로그인</v-btn>
                        </v-form>
                        <br>

                        <v-row>
                            <!--구글 로그인-->
                            <v-col cols="12" class="d-flex justify-center">
                                <img
                                    src="@/assets/logo/google_login.svg"
                                    style="max-height: 40px; width: auto;"
                                    @click="googleLogin()"    
                                />
                            </v-col>
                            <!--카카오 로그인-->
                            <v-col cols="12" class="d-flex justify-center">
                                <img
                                    src="@/assets/logo/kakao_login_large_wide.png"
                                    style="max-height: 40px; width: auto;"
                                    @click="kakaoLogin()"    
                                />
                            </v-col>
                        </v-row>
<!--
                        <v-row>
                        <v-col cols="12" class="d-flex">
                            <div class="full-btn" @click="googleLogin">
                            <img src="@/assets/logo/google_login.svg" alt="Sign in with Google" />
                            </div>
                        </v-col>
                        <v-col cols="12" class="d-flex">
                            <div class="full-btn" @click="kakaoLogin">
                            <img src="@/assets/logo/kakao_login_large_wide.png" alt="카카오로 로그인" />
                            </div>
                        </v-col>
                        </v-row>-->
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>>
</template>

<script>
import axios from 'axios';

export default{
    data(){
        return {
            email : "",
            password : ""
        }
    },
    methods:{
        async userLogin(){
            const loginData = {
                email: this.email,
                password: this.password
            }
            const response = await axios.post("http://localhost:8080/user/login", loginData);
            const token = response.data.token;
            localStorage.setItem("token", token);
            window.location.href = "/";
        },

        googleLogin() {

        },
        kakaoLogin() {

        }
    }
}
</script>


<style>
.full-btn{
  width:100%;
  height:44px;           /* 두 버튼 높이 동일 */
  display:flex;
  align-items:center;
  justify-content:center;
  border-radius:6px;
}
.full-btn img{
  width:100%;
  height:100%;
  object-fit:contain;    /* 비율 유지하며 컨테이너에 맞춤 */
  display:block;
}

</style>