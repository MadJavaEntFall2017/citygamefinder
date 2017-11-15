<%@include file="taglib.jsp"%>
<div class="container">

    <form class="form-horizontal" action="showResults" method="post"  id="city_form">

        <%-- Form Title --%>

        <legend>City Game Finder</legend>

        <br>

        <%-- Zip Code --%>

        <div class="form-group row">
            <label for="zipcode-input" class="col-sm-2 col-form-label">I will be in: </label>
            <div class="col-sm-2">
                <input class="form-control" type="number" name="zipcode-input" id="zipcode-input"
                       placeholder="Zip Code">
            </div>
        </div>

        <%-- From Date --%>

        <div class="form-group row">
            <label for="from-date-input" class="col-sm-2 col-form-label">From:</label>
            <div class="col-sm-4">
                <input class="form-control" type="date" name="from-date-input" id="from-date-input" >
            </div>
        </div>

        <%-- To Date --%>

        <div class="form-group row">
            <label for="to-date-input" class="col-sm-2 col-form-label">To:</label>
            <div class="col-sm-4">
                <input class="form-control" type="date" name="to-date-input" id="to-date-input" >
            </div>
        </div>

        <%-- Mile Select --%>

        <div class="form-group row">
            <label for="mile-input" class="col-sm-2 col-form-label">Find Games Within: </label>
            <div class="col-sm-2">
                <input class="form-control" type="number" name="mile-input" id="mile-input"
                       placeholder="Miles">
            </div>
        </div>

        <%-- Sport Select --%>

        <div class="form-group row">
            <label for="sport-input" class="col-sm-2 col-form-label">Select Sport:</label>
            <div class="col-sm-2">
                <select class="form-control" name="sport-input" id="sport-input">
                    <option></option>
                    <c:forEach items="${sports}" var="sport">
                        <option>${sport}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <%-- Button --%>

        <div class="form-group row">
            <div class="col-sm-4 offset-2">
                <button type="submit" class="btn btn-primary" >Search<span class="glyphicon glyphicon-search"></span></button>
            </div>
        </div>
    </form>

    <script>
        function onSubmit()
        {
            var zip = document.getElementById("zipcode-input").value;
            var fromDate = document.getElementById("from-date-input").value;
            var toDate = document.getElementById("to-date-input").value;
            var mile = document.getElementById("mile-input").value;
            var e = document.getElementById("sport-input");
            var sport = e.options[e.selectedIndex].value;

            if (zip == "" && fromDate == "" && toDate == "" && mile == "" && sport == "") {
                alert('Please select at least one item before submitting');
                return false;
            }
        }
        $('#city_form').submit(onSubmit)
    </script>

</div>

