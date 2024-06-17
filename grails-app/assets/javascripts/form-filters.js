const atlasFilterForm = document.querySelector('atlas-filter-form')

const base_url = "http://localhost:8080/payment/index?"

atlasFilterForm.addEventListener("atlas-apply-filter", (e) => {
    const filters = {}
    if (e.detail.filterData.status) filters.status = e.detail.filterData.status
    window.location.href = base_url + new URLSearchParams(filters).toString()
})

atlasFilterForm.addEventListener("atlas-clean-filter", (e) => {
    window.location.href = base_url
})