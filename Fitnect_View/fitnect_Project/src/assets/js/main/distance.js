// src/utils/distance.js

export const calculateDistance = (origin, destination) => {
  return new Promise((resolve, reject) => {
    const directions = new kakao.maps.services.Directions();
    const start = new kakao.maps.LatLng(origin.lat, origin.lng);
    const end = new kakao.maps.LatLng(destination.lat, destination.lng);

    const callback = (result, status) => {
      if (status === kakao.maps.services.Status.OK) {
        const distance = result.routes[0].summary.distance;
        resolve(distance);
      } else {
        reject(new Error("Failed to calculate distance"));
      }
    };

    directions.route(
      {
        origin: start,
        destination: end,
        type: kakao.maps.services.Directions.Type.PEDESTRIAN,
      },
      callback
    );
  });
};
