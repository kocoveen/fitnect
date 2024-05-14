<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-6 col-md-6 col-xs-12">
        <div id="map">
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3165.2811766693585!2d127.03702261207383!3d37.50128597193896!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca3ff67128961%3A0x55a56e8ffc5bc5d!2z66mA7Yuw7Lqg7Y287IqkIOyXreyCvA!5e0!3m2!1sko!2sus!4v1715501936405!5m2!1sko!2sus"
            width="600"
            height="450"
            style="border: 0"
            allowfullscreen=""
            loading="lazy"
            referrerpolicy="no-referrer-when-downgrade"
          ></iframe>
        </div>
      </div>
      <div class="col-lg-6 col-md-6 col-xs-12">
        <div class="contact-form">
          <form id="contact" action="" method="post">
            <div class="row">
              <div class="col-md-6 col-sm-12">
                <fieldset>
                  <input name="name" type="text" id="name" placeholder="Your Name*" required="" />
                </fieldset>
              </div>
              <div class="col-md-6 col-sm-12">
                <fieldset>
                  <input name="email" type="text" id="email" pattern="[^ @]*@[^ @]*" placeholder="Your Email*" required="" />
                </fieldset>
              </div>
              <div class="col-md-12 col-sm-12">
                <fieldset>
                  <input name="subject" type="text" id="subject" placeholder="Subject" />
                </fieldset>
              </div>
              <div class="col-lg-12">
                <fieldset>
                  <textarea name="message" rows="6" id="message" placeholder="Message" required=""></textarea>
                </fieldset>
              </div>
              <div class="col-lg-12">
                <fieldset>
                  <button type="submit" id="form-submit" class="main-button">Send Message</button>
                </fieldset>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
#contact-us .container-fluid .col-lg-6 {
  padding: 0px;
}

@media (max-width: 992px) {
  #contact-us #map {
    margin-bottom: -7px;
  }
}

#map > iframe {
  width: 100%;
  height: 100%;
}

@media (max-width: 992px) {
  #contact-us .contact-form {
    padding: 30px;
  }
}

#contact-us .contact-form {
  padding: 80px;
  background-image: url(@/assets/imgs/contact-bg.jpg);
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100%;
}

#contact-us .contact-form #contact {
  background-color: #fff;
  padding: 40px;
  border-radius: 5px;
}

.contact-form input,
.contact-form textarea {
  color: #7a7a7a;
  font-size: 13px;
  border: 1px solid #ddd;
  background-color: #fff;
  width: 100%;
  height: 40px;
  outline: none;
  line-height: 40px;
  padding: 0px 10px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  margin-bottom: 30px;
}

.contact-form button {
  display: inline-block;
  font-size: 13px;
  padding: 11px 17px;
  background-color: #25c3d3;
  color: #fff;
  text-align: center;
  font-weight: 400;
  text-transform: uppercase;
  transition: all 0.3s;
  border: none;
  outline: none;
  margin-top: -8px;
  font-weight: bold;
}

#map {
  height: 100%;
}

.btn-lg-square {
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: normal;
  border-radius: 50%;
}

.btn.btn-primary {
  box-shadow: inset 0 0 0 0 var(--bs-primary);
}

.btn-lg-square {
  width: 56px;
  height: 56px;
}

.btn {
  font-weight: 600;
  transition: 0.5s;
}
</style>
