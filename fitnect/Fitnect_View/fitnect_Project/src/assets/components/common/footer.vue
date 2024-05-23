<template>
  <div class="container-fluid footer py-5 wow fadeIn" data-wow-delay="0.2s">
    <div class="container py-5">
      <div class="row g-5">
        <div class="col-md-6 col-lg-6 col-xl-3">
          <div class="footer-item d-flex flex-column">
            <h4 class="text-white mb-4"><i class="fas fa-star-of-life me-3"></i>Fitnect</h4>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorem impedit eos autem dolores laudantium quia, qui similique</p>
            <div class="d-flex align-items-center">
              <i class="fas fa-share fa-2x text-white me-2"></i>
              <a class="btn-square btn btn-primary text-white rounded-circle mx-1" href=""><i class="fab fa-facebook-f"></i></a>
              <a class="btn-square btn btn-primary text-white rounded-circle mx-1" href=""><i class="fab fa-twitter"></i></a>
              <a class="btn-square btn btn-primary text-white rounded-circle mx-1" href=""><i class="fab fa-instagram"></i></a>
              <a class="btn-square btn btn-primary text-white rounded-circle mx-1" href=""><i class="fab fa-linkedin-in"></i></a>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-6 col-xl-3">
          <div class="footer-item d-flex flex-column">
            <h4 class="mb-4 text-white">Quick Links</h4>
            <a href=""><i class="fas fa-angle-right me-2"></i> About Us</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Contact Us</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Privacy Policy</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Terms & Conditions</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Our Blog & News</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Our Team</a>
          </div>
        </div>
        <div class="col-md-6 col-lg-6 col-xl-3">
          <div class="footer-item d-flex flex-column">
            <h4 class="mb-4 text-white">Fitnect Services</h4>
            <a href=""><i class="fas fa-angle-right me-2"></i> All Services</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Physiotherapy</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Diagnostics</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Manual Therapy</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Massage Therapy</a>
            <a href=""><i class="fas fa-angle-right me-2"></i> Rehabilitation</a>
          </div>
        </div>
        <div class="col-md-6 col-lg-6 col-xl-3">
          <div class="footer-item d-flex flex-column">
            <h4 class="mb-4 text-white">Contact Info</h4>
            <a href=""
              ><i
                class="fa fa-map-marker-alt me-2"
                style="font-weight: 900; font-family: 'Font Awesome 5 Free'; font-style: normal; font-variant: normal; line-height: 1"
              ></i>
              123 Street, New York, USA</a
            >
            <a href=""><i class="fas fa-envelope me-2"></i> info@example.com</a>
            <a href=""><i class="fas fa-envelope me-2"></i> info@example.com</a>
            <a href=""><i class="fas fa-phone me-2"></i> +012 345 67890</a>
            <a href="" class="mb-3"><i class="fas fa-print me-2"></i> +012 345 67890</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.footer {
  background: linear-gradient(rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0.9)), url(../img/carousel-2.jpg);
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
}
.footer .footer-item a {
  line-height: 35px;
  color: var(--bs-body);
  transition: 0.5s;
}

.footer .footer-item p {
  line-height: 35px;
}

.footer .footer-item a:hover {
  letter-spacing: 2px;
  color: var(--bs-primary);
}

.mx-1 {
  display: flex !important;
}
</style>
