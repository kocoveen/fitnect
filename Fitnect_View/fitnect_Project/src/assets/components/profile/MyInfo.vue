<template>
  <div class="container">
    <div class="row gutters-sm">
      <div class="col-md-4 d-none d-md-block">
        <div class="card">
          <div class="card-body">
            <nav class="nav flex-column nav-pills nav-gap-y-1">
              <a
                href="#profile"
                data-toggle="tab"
                class="nav-item nav-link has-icon nav-link-faded active"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  class="feather feather-user mr-2"
                >
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                  <circle cx="12" cy="7" r="4"></circle></svg
                >내 정보
              </a>
              <a
                href="#account"
                data-toggle="tab"
                class="nav-item nav-link has-icon nav-link-faded"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  class="feather feather-settings mr-2"
                >
                  <circle cx="12" cy="12" r="3"></circle>
                  <path
                    d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"
                  ></path></svg
                >등록한 곳
              </a>
              <a
                href="#security"
                data-toggle="tab"
                class="nav-item nav-link has-icon nav-link-faded"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  class="feather feather-shield mr-2"
                >
                  <path
                    d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"
                  ></path></svg
                >등록한 강의
              </a>
              <a
                href="#notification"
                data-toggle="tab"
                class="nav-item nav-link has-icon nav-link-faded"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  class="feather feather-bell mr-2"
                >
                  <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
                  <path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg
                >내가 쓴 체육관 리뷰
              </a>
              <a
                href="#billing"
                data-toggle="tab"
                class="nav-item nav-link has-icon nav-link-faded"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  class="feather feather-credit-card mr-2"
                >
                  <rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect>
                  <line x1="1" y1="10" x2="23" y2="10"></line></svg
                >내가 쓴 트레이너 리뷰
              </a>
            </nav>
          </div>
        </div>
      </div>
      <div class="col-md-8">
        <div class="card">
          <div class="card-header border-bottom mb-3 d-flex d-md-none">
            <ul
              class="nav nav-tabs card-header-tabs nav-gap-x-1"
              role="tablist"
            >
              <li class="nav-item">
                <a
                  href="#profile"
                  data-toggle="tab"
                  class="nav-link has-icon active"
                  ><svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="feather feather-user"
                  >
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                    <circle cx="12" cy="7" r="4"></circle></svg
                ></a>
              </li>
              <li class="nav-item">
                <a href="#account" data-toggle="tab" class="nav-link has-icon"
                  ><svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="feather feather-settings"
                  >
                    <circle cx="12" cy="12" r="3"></circle>
                    <path
                      d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"
                    ></path></svg
                ></a>
              </li>
              <li class="nav-item">
                <a href="#security" data-toggle="tab" class="nav-link has-icon"
                  ><svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="feather feather-shield"
                  >
                    <path
                      d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"
                    ></path></svg
                ></a>
              </li>
              <li class="nav-item">
                <a
                  href="#notification"
                  data-toggle="tab"
                  class="nav-link has-icon"
                  ><svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="feather feather-bell"
                  >
                    <path
                      d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"
                    ></path>
                    <path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg
                ></a>
              </li>
              <li class="nav-item">
                <a href="#billing" data-toggle="tab" class="nav-link has-icon"
                  ><svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="feather feather-credit-card"
                  >
                    <rect
                      x="1"
                      y="4"
                      width="22"
                      height="16"
                      rx="2"
                      ry="2"
                    ></rect>
                    <line x1="1" y1="10" x2="23" y2="10"></line></svg
                ></a>
              </li>
            </ul>
          </div>
          <div class="card-body tab-content">
            <div class="tab-pane active" id="profile">
              <h6>YOUR PROFILE INFORMATION</h6>
              <hr />
              <form>
                <div class="form-group">
                  <label for="fullName">Full Name</label>
                  <input
                    type="text"
                    class="form-control"
                    id="fullName"
                    aria-describedby="fullNameHelp"
                    placeholder="Enter your fullname"
                    value="Kenneth Valdez"
                  />
                  <small id="fullNameHelp" class="form-text text-muted"
                    >Your name may appear around here where you are mentioned.
                    You can change or remove it at any time.</small
                  >
                </div>
                <div class="form-group">
                  <label for="bio">Your Bio</label>
                  <textarea
                    class="form-control autosize"
                    id="bio"
                    placeholder="Write something about you"
                    style="
                      overflow: hidden;
                      overflow-wrap: break-word;
                      resize: none;
                      height: 62px;
                    "
                  >
A front-end developer that focus more on user interface design, a web interface wizard, a connector of awesomeness.</textarea
                  >
                </div>
                <div class="form-group">
                  <label for="url">URL</label>
                  <input
                    type="text"
                    class="form-control"
                    id="url"
                    placeholder="Enter your website address"
                    value="http://benije.ke/pozzivkij"
                  />
                </div>
                <div class="form-group">
                  <label for="location">Location</label>
                  <input
                    type="text"
                    class="form-control"
                    id="location"
                    placeholder="Enter your location"
                    value="Bay Area, San Francisco, CA"
                  />
                </div>
                <div class="form-group small text-muted">
                  All of the fields on this page are optional and can be deleted
                  at any time, and by filling them out, you're giving us consent
                  to share this data wherever your user profile appears.
                </div>
                <button type="button" class="btn btn-primary">
                  Update Profile
                </button>
                <button type="reset" class="btn btn-light">
                  Reset Changes
                </button>
              </form>
            </div>
            <div class="tab-pane" id="account">
              <h6>ACCOUNT SETTINGS</h6>
              <hr />
              <form>
                <div class="form-group">
                  <label for="username">Username</label>
                  <input
                    type="text"
                    class="form-control"
                    id="username"
                    aria-describedby="usernameHelp"
                    placeholder="Enter your username"
                    value="kennethvaldez"
                  />
                  <small id="usernameHelp" class="form-text text-muted"
                    >After changing your username, your old username becomes
                    available for anyone else to claim.</small
                  >
                </div>
                <hr />
                <div class="form-group">
                  <label class="d-block text-danger">Delete Account</label>
                  <p class="text-muted font-size-sm">
                    Once you delete your account, there is no going back. Please
                    be certain.
                  </p>
                </div>
                <button class="btn btn-danger" type="button">
                  Delete Account
                </button>
              </form>
            </div>
            <div class="tab-pane" id="security">
              <h6>SECURITY SETTINGS</h6>
              <hr />
              <form>
                <div class="form-group">
                  <label class="d-block">Change Password</label>
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Enter your old password"
                  />
                  <input
                    type="text"
                    class="form-control mt-1"
                    placeholder="New password"
                  />
                  <input
                    type="text"
                    class="form-control mt-1"
                    placeholder="Confirm new password"
                  />
                </div>
              </form>
              <hr />
              <form>
                <div class="form-group">
                  <label class="d-block">Two Factor Authentication</label>
                  <button class="btn btn-info" type="button">
                    Enable two-factor authentication
                  </button>
                  <p class="small text-muted mt-2">
                    Two-factor authentication adds an additional layer of
                    security to your account by requiring more than just a
                    password to log in.
                  </p>
                </div>
              </form>
              <hr />
              <form>
                <div class="form-group mb-0">
                  <label class="d-block">Sessions</label>
                  <p class="font-size-sm text-secondary">
                    This is a list of devices that have logged into your
                    account. Revoke any sessions that you do not recognize.
                  </p>
                  <ul class="list-group list-group-sm">
                    <li class="list-group-item has-icon">
                      <div>
                        <h6 class="mb-0">San Francisco City 190.24.335.55</h6>
                        <small class="text-muted"
                          >Your current session seen in United States</small
                        >
                      </div>
                      <button
                        class="btn btn-light btn-sm ml-auto"
                        type="button"
                      >
                        More info
                      </button>
                    </li>
                  </ul>
                </div>
              </form>
            </div>
            <div class="tab-pane" id="notification">
              <h6>NOTIFICATION SETTINGS</h6>
              <hr />
              <form>
                <div class="form-group">
                  <label class="d-block mb-0">Security Alerts</label>
                  <div class="small text-muted mb-3">
                    Receive security alert notifications via email
                  </div>
                  <div class="custom-control custom-checkbox">
                    <input
                      type="checkbox"
                      class="custom-control-input"
                      id="customCheck1"
                      checked=""
                    />
                    <label class="custom-control-label" for="customCheck1"
                      >Email each time a vulnerability is found</label
                    >
                  </div>
                  <div class="custom-control custom-checkbox">
                    <input
                      type="checkbox"
                      class="custom-control-input"
                      id="customCheck2"
                      checked=""
                    />
                    <label class="custom-control-label" for="customCheck2"
                      >Email a digest summary of vulnerability</label
                    >
                  </div>
                </div>
                <div class="form-group mb-0">
                  <label class="d-block">SMS Notifications</label>
                  <ul class="list-group list-group-sm">
                    <li class="list-group-item has-icon">
                      Comments
                      <div
                        class="custom-control custom-control-nolabel custom-switch ml-auto"
                      >
                        <input
                          type="checkbox"
                          class="custom-control-input"
                          id="customSwitch1"
                          checked=""
                        />
                        <label
                          class="custom-control-label"
                          for="customSwitch1"
                        ></label>
                      </div>
                    </li>
                    <li class="list-group-item has-icon">
                      Updates From People
                      <div
                        class="custom-control custom-control-nolabel custom-switch ml-auto"
                      >
                        <input
                          type="checkbox"
                          class="custom-control-input"
                          id="customSwitch2"
                        />
                        <label
                          class="custom-control-label"
                          for="customSwitch2"
                        ></label>
                      </div>
                    </li>
                    <li class="list-group-item has-icon">
                      Reminders
                      <div
                        class="custom-control custom-control-nolabel custom-switch ml-auto"
                      >
                        <input
                          type="checkbox"
                          class="custom-control-input"
                          id="customSwitch3"
                          checked=""
                        />
                        <label
                          class="custom-control-label"
                          for="customSwitch3"
                        ></label>
                      </div>
                    </li>
                    <li class="list-group-item has-icon">
                      Events
                      <div
                        class="custom-control custom-control-nolabel custom-switch ml-auto"
                      >
                        <input
                          type="checkbox"
                          class="custom-control-input"
                          id="customSwitch4"
                          checked=""
                        />
                        <label
                          class="custom-control-label"
                          for="customSwitch4"
                        ></label>
                      </div>
                    </li>
                    <li class="list-group-item has-icon">
                      Pages You Follow
                      <div
                        class="custom-control custom-control-nolabel custom-switch ml-auto"
                      >
                        <input
                          type="checkbox"
                          class="custom-control-input"
                          id="customSwitch5"
                        />
                        <label
                          class="custom-control-label"
                          for="customSwitch5"
                        ></label>
                      </div>
                    </li>
                  </ul>
                </div>
              </form>
            </div>
            <div class="tab-pane" id="billing">
              <h6>BILLING SETTINGS</h6>
              <hr />
              <form>
                <div class="form-group">
                  <label class="d-block mb-0">Payment Method</label>
                  <div class="small text-muted mb-3">
                    You have not added a payment method
                  </div>
                  <button class="btn btn-info" type="button">
                    Add Payment Method
                  </button>
                </div>
                <div class="form-group mb-0">
                  <label class="d-block">Payment History</label>
                  <div
                    class="border border-gray-500 bg-gray-200 p-3 text-center font-size-sm"
                  >
                    You have not made any payment.
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div id="login-body" style="background-color: #ddebeab8">
    <div id="info">
      <div id="sign-text">
        <span> 내 정보 </span>
      </div>
      <div id="input-area">
        <span>EMAIL</span>
        <input type="text" v-model="user.email" class="inputbox" readonly />

        <span>NAME</span>
        <input
          type="text"
          v-model="user.name"
          class="inputbox"
          placeholder="Enter your name"
        />

        <span>PHONE</span>
        <input
          type="text"
          v-model="user.phone"
          class="inputbox"
          placeholder="Enter your number"
        />

        <div id="address">
          <span>ADDRESS</span
          ><button id="postcode" @click="openPostcode">주소 찾기</button>
        </div>
        <input
          type="text"
          class="inputbox"
          v-model="user.address"
          placeholder="Enter your adress"
          readonly
        />
      </div>

      <span>키</span>
      <div id="height">
        <input type="text" class="inputbox" v-model="user.height" />
      </div>

      <span>몸무게</span>
      <div id="weight">
        <input type="text" class="inputbox" v-model="user.weight" />
      </div>

      <div>
        <button id="changePassword" @click="modalOpen">패스워드 변경</button>
        <button id="nav-area" @click="changeInfo">정보 수정</button>
        <button id="nav-area" @click="deleteInfo">회원 탈퇴</button>
      </div>
    </div>
  </div>

  <div class="modal-wrap" v-show="modalCheck">
    <div class="modal-container">
      <!--  모달창 content  -->
      <span>변경할 패스워드</span>
      <input
        type="text"
        class="inputbox"
        v-model="userIdAndPassword.password"
      />
      <div class="modal-btn">
        <button @click="changePassword">패스워드 변경</button>
        <button @click="modalOpen">취소</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const accessToken = sessionStorage.getItem("accessToken");
const loginUser = decodeJWT(accessToken);
const user = ref({});

const myId = loginUser.payload.userId;

axios
  .get(`http://localhost:8080/user/${myId}`, {
    headers: {
      Authorization: `bearer ${accessToken}`,
    },
  })
  .then((response) => {
    user.value = response.data.data;
  })
  .catch((e) => {});

const roadAddress = ref("");
const openPostcode = () => {
  new window.daum.Postcode({
    oncomplete: (data) => {
      roadAddress.value = data.roadAddress;
      loadCoords(data.roadAddress);
    },
  }).open();
};

const loadCoords = (address) => {
  axios
    .get(
      `https://dapi.kakao.com/v2/local/search/address.json?query=${address}`,
      {
        headers: {
          Authorization: "KakaoAK d00a06f293cd0dd71929545bb8a71c1c",
        },
      }
    )
    .then((response) => {
      user.value.address = response.data.documents[0]["address_name"]; // 도로명 주소
      user.value.longitude = response.data.documents[0]["y"]; // 위도
      user.value.latitude = response.data.documents[0]["x"]; // 경도
    })
    .catch((e) => {
      alert(e);
    });
};

const modalCheck = ref(false);

const modalOpen = () => {
  modalCheck.value = !modalCheck.value;
};

const userIdAndPassword = ref({
  userId: myId,
  password: "",
});

const changePassword = () => {
  axios
    .put(
      `http://localhost:8080/user/change-password`,
      userIdAndPassword.value,
      {
        headers: {
          Authorization: `bearer ${accessToken}`,
        },
      }
    )
    .then((response) => {
      alert("패스워드 변경이 완료되었습니다.");
      router.replace("/profile/my-info");
      modalCheck.value = false;
    });
};

const changeInfo = () => {
  axios
    .put(`http://localhost:8080/user/${myId}`, user.value, {
      headers: {
        Authorization: `bearer ${accessToken}`,
      },
    })
    .then((response) => {
      alert("정보 변경이 완료되었습니다.");
      router.replace("/profile/my-info");
    });
};

const deleteInfo = () => {
  axios
    .delete(`http://localhost:8080/user/${myId}`, {
      headers: {
        Authorization: `bearer ${accessToken}`,
      },
    })
    .then((response) => {
      alert("탈퇴가 완료되었습니다.");
      sessionStorage.removeItem("accessToken");
      router.push("/");
    });
};

// JWT 디코딩 함수
function base64UrlDecode(str) {
  return decodeURIComponent(
    atob(str.replace(/-/g, "+").replace(/_/g, "/"))
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );
}

function decodeJWT(token) {
  try {
    const [header, payload, signature] = token.split(".");
    const decodedHeader = JSON.parse(base64UrlDecode(header));
    const decodedPayload = JSON.parse(base64UrlDecode(payload));

    return {
      header: decodedHeader,
      payload: decodedPayload,
      signature: signature,
    };
  } catch (error) {
    console.error("Invalid token:", error);
    return null;
  }
}
</script>

<style scoped>
#sign-text {
  display: flex;
  justify-content: center;
}

#info {
  display: flex;
  align-items: center;
  flex-direction: column;
}

#input-area {
  display: flex;
  align-items: center;
  flex-direction: column;
}

/* dimmed */
.modal-wrap {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
}
/* modal or popup */
.modal-container {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 550px;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
}
</style>
