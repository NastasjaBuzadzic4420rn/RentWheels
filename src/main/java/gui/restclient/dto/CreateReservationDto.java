package gui.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class CreateReservationDto {
        @JsonProperty("user_id")
        private Long userId;
        @JsonProperty("company_vehicle_id")
        private Long companyVehicleId;
        @JsonProperty("priceP_with_discount")
        private double priceWithDiscount;
        @JsonProperty("start_date")
        private Long startDate;
        @JsonProperty("end_date")
        private Long endDate;

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public Long getCompanyVehicleId() {
                return companyVehicleId;
        }

        public void setCompanyVehicleId(Long companyVehicleId) {
                this.companyVehicleId = companyVehicleId;
        }

        public double getPriceWithDiscount() {
                return priceWithDiscount;
        }

        public void setPriceWithDiscount(double priceWithDiscount) {
                this.priceWithDiscount = priceWithDiscount;
        }

        public Long getStartDate() {
                return startDate;
        }

        public void setStartDate(Long startDate) {
                this.startDate = startDate;
        }

        public Long getEndDate() {
                return endDate;
        }

        public void setEndDate(Long endDate) {
                this.endDate = endDate;
        }
}
