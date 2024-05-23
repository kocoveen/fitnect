<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-6 offset-lg-3">
        <div class="section-heading">
          <h2>Expert <em>Trainers</em></h2>
          <img src="@/assets/imgs/line-dec.png" alt="" />
          <p>Nunc urna sem, laoreet ut metus id, aliquet consequat magna. Sed viverra ipsum dolor, ultricies fermentum massa consequat eu.</p>
        </div>
      </div>
    </div>
    <div class="row">
      <div style="display: flex; justify-content: space-around">
        <div class="col-lg-4">
          <div class="trainer-item">
            <div class="image-thumb">
              <img src="@/assets/imgs/climing.jpg" alt="" />
            </div>
            <div class="down-content">
              <span>The King God General Programmer</span>
              <h4>Jin S. OCK</h4>
              <p>Bitters cliche tattooed 8-bit distillery mustache. Keytar succulents gluten-free vegan church-key pour-over seitan flannel.</p>
              <ul class="social-icons">
                <li>
                  <a href="#"><i class="fa fa-facebook"></i></a>
                </li>
                <li>
                  <a href="#"><i class="fa fa-twitter"></i></a>
                </li>
                <li>
                  <a href="#"><i class="fa fa-linkedin"></i></a>
                </li>
                <li>
                  <a href="#"><i class="fa fa-behance"></i></a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="trainer-item">
            <div class="image-thumb">
              <img src="@/assets/imgs/ungki.jpg" alt="" />
            </div>
            <div class="down-content">
              <span>Just Dumbel Pull Over</span>
              <h4>UNGKI JANG</h4>
              <p>Bitters cliche tattooed 8-bit distillery mustache. Keytar succulents gluten-free vegan church-key pour-over seitan flannel.</p>
              <ul class="social-icons">
                <li>
                  <a href="#"><i class="fa fa-facebook"></i></a>
                </li>
                <li>
                  <a href="#"><i class="fa fa-twitter"></i></a>
                </li>
                <li>
                  <a href="#"><i class="fa fa-linkedin"></i></a>
                </li>
                <li>
                  <a href="#"><i class="fa fa-behance"></i></a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>
<style scoped>
/* about */
.section-heading {
  text-align: center;
  margin-top: 140px;
  margin-bottom: 80px;
}

.section-heading h2 {
  font-size: 28px;
  font-weight: 800;
  color: #232d39;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin-top: 0px;
  margin-bottom: 0px;
}

.section-heading img {
  margin: 20px auto;
}

p {
  font-size: 14px;
  line-height: 25px;
  color: #7a7a7a;
}

.trainer-item {
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
  padding: 40px;
}

.trainer-item img {
  width: 100%;
  border-radius: 5px;
}

.trainer-item span {
  font-size: 13px;
  font-weight: 500;
  color: #ed563b;
  display: inline-block;
  margin-top: 25px;
  margin-bottom: 10px;
}

.trainer-item h4 {
  font-size: 19px;
  font-weight: 600;
  color: #232d39;
  letter-spacing: 0.5px;
  margin-bottom: 18px;
}

.trainer-item p {
  margin-bottom: 20px;
  font-size: 14px;
  line-height: 25px;
  color: #7a7a7a;
}

ul {
  margin-bottom: 0px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.trainer-item ul.social-icons li {
  display: inline-block;
  margin-right: 12px;
}

.trainer-item ul.social-icons li a {
  color: #232d39;
  transition: all 0.3s;
  text-decoration: none !important;
}
</style>
